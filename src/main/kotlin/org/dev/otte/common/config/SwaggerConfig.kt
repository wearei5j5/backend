package org.dev.otte.common.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(title = "OTTE API 명세서", description = "AI 기반 맞춤형 영화추천 서비스")
)
@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val securityScheme = SecurityScheme().apply {
            type = SecurityScheme.Type.HTTP
            scheme = "Bearer"
            bearerFormat = "JWT"
            `in` = SecurityScheme.In.HEADER
            name = "Authorization"
        }
        val securityRequirement = SecurityRequirement().addList("access_token")
        return OpenAPI()
            .components(
                Components().addSecuritySchemes("access_token", securityScheme)
            )
            .security(listOf(securityRequirement))
    }
}