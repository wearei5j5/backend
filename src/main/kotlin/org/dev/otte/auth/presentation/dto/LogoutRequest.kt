package org.dev.otte.auth.presentation.dto

data class LogoutRequest(
    val refreshToken: String
)