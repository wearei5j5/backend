package org.dev.otte.movie.infra.clova.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("clova")
data class ClovaStudioProperties(
    val clovaStudioApiKey: String,
    val apiGatewayApiKey: String,
    val clovaStudioRequestId: String
)