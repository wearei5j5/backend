package org.dev.otte.common.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ClientBaseUrlRequest(
    @Schema(description = "변경할 url", example = "https://frontend-nine-brown.vercel.app/onboarding")
    val url: String
)

