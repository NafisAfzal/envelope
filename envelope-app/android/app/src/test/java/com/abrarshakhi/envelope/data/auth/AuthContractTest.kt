package com.abrarshakhi.envelope.data.auth

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Contract tests: backend-shaped JSON decodes into the Android mirrors.
 * All fixtures are synthetic; nothing here resembles real credentials.
 */
class AuthContractTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `auth envelope with keys decodes`() {
        val raw = """
            {
              "success": true,
              "message": "Signed in successfully.",
              "data": {
                "accessToken": "SYNTHETIC_ACCESS",
                "refreshToken": "SYNTHETIC_REFRESH",
                "tokenType": "Bearer",
                "expiresIn": 900,
                "user": {
                  "id": 7,
                  "username": "synth_user",
                  "email": "synth@example.invalid",
                  "role": "USER",
                  "isEmailVerified": true,
                  "createdAt": "2026-09-07T00:00:00Z"
                },
                "keys": {
                  "publicKey": "SYNTHETIC_PUBKEY",
                  "encryptedMasterKey": "SYNTHETIC_ENC_MASTER",
                  "encryptedPrivateKey": "SYNTHETIC_ENC_PRIV",
                  "encryptedRecoveryKey": "SYNTHETIC_ENC_REC",
                  "keyVersion": 1
                }
              },
              "timestamp": "2026-09-07T00:00:01Z"
            }
        """.trimIndent()

        val envelope = json.decodeFromString<ApiEnvelope<AuthDto>>(raw)

        assertTrue(envelope.success)
        val auth = envelope.data!!
        assertEquals("SYNTHETIC_ACCESS", auth.accessToken)
        assertEquals("Bearer", auth.tokenType)
        assertEquals(900L, auth.expiresIn)
        assertEquals("synth_user", auth.user.username)
        assertTrue(auth.user.isEmailVerified)
        assertEquals("SYNTHETIC_ENC_MASTER", auth.keys!!.encryptedMasterKey)
        assertEquals(1, auth.keys.keyVersion)
    }

    @Test
    fun `auth envelope without keys decodes keys as null`() {
        val raw = """
            {
              "success": true,
              "message": "Tokens refreshed successfully.",
              "data": {
                "accessToken": "SYNTHETIC_ACCESS",
                "refreshToken": "SYNTHETIC_REFRESH",
                "expiresIn": 900,
                "user": {
                  "id": 7,
                  "username": "synth_user",
                  "email": "synth@example.invalid",
                  "role": "USER",
                  "isEmailVerified": true,
                  "createdAt": "2026-09-07T00:00:00Z"
                }
              }
            }
        """.trimIndent()

        val envelope = json.decodeFromString<ApiEnvelope<AuthDto>>(raw)

        assertTrue(envelope.success)
        assertNull(envelope.data!!.keys)
        // tokenType default survives an absent field.
        assertEquals("Bearer", envelope.data.tokenType)
    }

    @Test
    fun `key bundle with null encryptedMasterKey decodes`() {
        val raw = """
            {
              "publicKey": "SYNTHETIC_PUBKEY",
              "encryptedMasterKey": null,
              "encryptedPrivateKey": "SYNTHETIC_ENC_PRIV",
              "encryptedRecoveryKey": "SYNTHETIC_ENC_REC",
              "keyVersion": 1
            }
        """.trimIndent()

        val keys = json.decodeFromString<UserKeysDto>(raw)

        assertNull(keys.encryptedMasterKey)
        assertEquals("SYNTHETIC_ENC_PRIV", keys.encryptedPrivateKey)
    }

    @Test
    fun `pre-login response decodes kdf parameters`() {
        val raw = """
            {
              "salt": "SYNTHETIC_SALT",
              "kdfAlgorithm": "ARGON2ID",
              "kdfIterations": 3,
              "kdfMemoryKb": 65536,
              "kdfParallelism": 4
            }
        """.trimIndent()

        val preLogin = json.decodeFromString<PreLoginDto>(raw)

        assertEquals("SYNTHETIC_SALT", preLogin.salt)
        assertEquals("ARGON2ID", preLogin.kdfAlgorithm)
        assertEquals(3, preLogin.kdfIterations)
        assertEquals(65536, preLogin.kdfMemoryKb)
        assertEquals(4, preLogin.kdfParallelism)
    }

    @Test
    fun `otp response decodes`() {
        val raw = """{"email": "synth@example.invalid", "expiresInSeconds": 600}"""

        val otp = json.decodeFromString<OtpDto>(raw)

        assertEquals("synth@example.invalid", otp.email)
        assertEquals(600L, otp.expiresInSeconds)
    }

    @Test
    fun `validation error envelope maps to Validation`() {
        val raw = """
            {
              "success": false,
              "message": "Validation failed",
              "data": {"username": "Username must be between 3 and 30 characters"}
            }
        """.trimIndent()

        val envelope = json.decodeFromString<ApiEnvelope<Map<String, String>>>(raw)

        assertFalse(envelope.success)
        val error = authErrorFor(400, envelope.message, fieldErrors = envelope.data!!)

        assertTrue(error is AuthApiError.Validation)
        assertEquals(
            "Username must be between 3 and 30 characters",
            (error as AuthApiError.Validation).fieldErrors["username"],
        )
    }

    @Test
    fun `rate-limit error maps to RateLimited with retry hint`() {
        val error = authErrorFor(
            statusCode = 429,
            message = "Rate limit exceeded. Please try again in 45 seconds.",
            retryAfterSeconds = 45L,
        )

        assertTrue(error is AuthApiError.RateLimited)
        assertEquals(45L, (error as AuthApiError.RateLimited).retryAfterSeconds)
    }

    @Test
    fun `invalid otp with count maps to InvalidOtp, without maps to BadRequest`() {
        val withCount = authErrorFor(400, "Invalid OTP", remainingAttempts = 3)
        assertTrue(withCount is AuthApiError.InvalidOtp)
        assertEquals(3, (withCount as AuthApiError.InvalidOtp).remainingAttempts)

        val withoutCount = authErrorFor(400, "Bad request")
        assertTrue(withoutCount is AuthApiError.BadRequest)
    }

    @Test
    fun `status codes map to typed errors`() {
        assertTrue(authErrorFor(401, "x") is AuthApiError.Unauthorized)
        assertTrue(authErrorFor(403, "x") is AuthApiError.Forbidden)
        assertTrue(authErrorFor(404, "x") is AuthApiError.NotFound)
        assertTrue(authErrorFor(409, "x") is AuthApiError.Conflict)
        assertTrue(authErrorFor(500, "x") is AuthApiError.Server)
        val unknown = authErrorFor(418, "x")
        assertTrue(unknown is AuthApiError.Unknown)
        assertEquals(418, (unknown as AuthApiError.Unknown).statusCode)
    }

    @Test
    fun `sign-in request encodes backend field names`() {
        val encoded = json.encodeToString(
            SignInRequest(username = "synth_user", clientAuthHash = "SYNTHETIC_HASH"),
        )

        assertTrue(encoded.contains("\"username\":\"synth_user\""))
        assertTrue(encoded.contains("\"clientAuthHash\":\"SYNTHETIC_HASH\""))
    }
}
