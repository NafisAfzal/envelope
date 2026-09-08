package com.abrarshakhi.envelope.auth.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Mirror of the backend generic response envelope
 * (`...engine.common.api.ApiResponse`):
 * `{ "success": ..., "message": ..., "data": ...?, "timestamp": ...? }`.
 *
 * `timestamp` is kept as a raw string so parsing never depends on the
 * server's instant format. No business logic lives here.
 */
@Serializable
data class ApiEnvelope<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val timestamp: String? = null,
)
