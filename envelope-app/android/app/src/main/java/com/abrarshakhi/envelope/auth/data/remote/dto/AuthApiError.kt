package com.abrarshakhi.envelope.auth.data.remote.dto

/**
 * Deterministic client-side representation of the VERIFIED backend failure
 * shapes (`...engine.common.exception.GlobalExceptionHandler`):
 * 400 validation (field -> message map), 400 invalid OTP (optional
 * `remainingAttempts`), 401, 403, 404, 409, 429 with `Retry-After` /
 * `X-RateLimit-*` response headers plus a details map, and 500 generic.
 *
 * Pure mapping only — no networking lives here. Unknown statuses map to
 * [AuthApiError.Unknown] rather than inventing codes.
 */
sealed interface AuthApiError {
    data class Validation(val message: String, val fieldErrors: Map<String, String>) : AuthApiError
    data class InvalidOtp(val message: String, val remainingAttempts: Int?) : AuthApiError
    data class BadRequest(val message: String) : AuthApiError
    data class Unauthorized(val message: String) : AuthApiError
    data class Forbidden(val message: String) : AuthApiError
    data class NotFound(val message: String) : AuthApiError
    data class Conflict(val message: String) : AuthApiError
    data class RateLimited(val message: String, val retryAfterSeconds: Long?) : AuthApiError
    data class Server(val message: String) : AuthApiError
    data class Unknown(val statusCode: Int, val message: String) : AuthApiError
}

/**
 * Maps a VERIFIED backend failure to [AuthApiError].
 *
 * @param fieldErrors the `data` map of a 400 validation response, if present.
 * @param remainingAttempts the `data.remainingAttempts` of a 400 invalid-OTP
 * response, if present.
 * @param retryAfterSeconds the `Retry-After` response header (also mirrored
 * in the 429 `data` map and `X-RateLimit-Reset`), if present.
 */
fun authErrorFor(
    statusCode: Int,
    message: String,
    fieldErrors: Map<String, String> = emptyMap(),
    remainingAttempts: Int? = null,
    retryAfterSeconds: Long? = null,
): AuthApiError = when (statusCode) {
    // A 400 with null/empty data is wire-identical for BadRequest and for an
    // invalid OTP without a remaining-attempts count; it maps to BadRequest.
    // Only a present remainingAttempts count identifies InvalidOtp.
    400 -> if (fieldErrors.isNotEmpty()) {
        AuthApiError.Validation(message, fieldErrors)
    } else if (remainingAttempts != null) {
        AuthApiError.InvalidOtp(message, remainingAttempts)
    } else {
        AuthApiError.BadRequest(message)
    }
    401 -> AuthApiError.Unauthorized(message)
    403 -> AuthApiError.Forbidden(message)
    404 -> AuthApiError.NotFound(message)
    409 -> AuthApiError.Conflict(message)
    429 -> AuthApiError.RateLimited(message, retryAfterSeconds)
    in 500..599 -> AuthApiError.Server(message)
    else -> AuthApiError.Unknown(statusCode, message)
}
