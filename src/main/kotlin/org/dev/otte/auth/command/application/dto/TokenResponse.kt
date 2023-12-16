package org.dev.otte.auth.command.application.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)