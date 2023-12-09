package org.dev.otte.movie.infra.clova.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("clova")
data class ClovaStudioConfig(
    val clovaStudioApiKey: String,
    val apiGatewayApiKey: String,
    val clovaStudioRequestId: String
)