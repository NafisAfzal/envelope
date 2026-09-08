package com.abrarshakhi.envelope.auth.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Android mirrors of the VERIFIED backend authentication DTOs
 * (`...engine.auth.dto.request` / `...engine.auth.dto.response`).
 *
 * Field names and nullability match the backend source exactly. Jackson on
 * the backend preserves Kotlin property names, so these camelCase names are
 * the wire names. `role` and `createdAt` stay raw strings to avoid coupling
 * to server enum/instant formats. No business logic lives here.
 */

// ---- Requests (login slice only) ----

@Serializable
data class PreLoginRequest(
    val identifier: String,
)

@Serializable
data class SignInRequest(
    val username: String,
    val clientAuthHash: String,
)

@Serializable
data class RefreshTokenRequest(
    val refreshToken: String,
)

@Serializable
data class SignUpInitRequest(
    val email: String,
    val username: String,
)

// ---- Responses ----

@Serializable
data class AuthDto(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String = "Bearer",
    val expiresIn: Long,
    val user: UserProfileDto,
    val keys: UserKeysDto? = null,
)

@Serializable
data class UserProfileDto(
    val id: Long,
    val username: String,
    val email: String,
    val role: String,
    val isEmailVerified: Boolean,
    val createdAt: String,
)

@Serializable
data class UserKeysDto(
    val publicKey: String,
    val encryptedMasterKey: String? = null,
    val encryptedPrivateKey: String,
    val encryptedRecoveryKey: String,
    val keyVersion: Int,
)

@Serializable
data class PreLoginDto(
    val salt: String,
    val kdfAlgorithm: String,
    val kdfIterations: Int,
    val kdfMemoryKb: Int,
    val kdfParallelism: Int,
)

@Serializable
data class OtpDto(
    val email: String,
    val expiresInSeconds: Long,
)

@Serializable
data class PasswordResetVerifyDto(
    val resetToken: String,
    val expiresInSeconds: Long,
)
