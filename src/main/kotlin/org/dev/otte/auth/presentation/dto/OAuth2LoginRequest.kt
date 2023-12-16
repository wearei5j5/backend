package org.dev.otte.auth.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.dev.otte.auth.command.application.dto.OAuth2LoginCommand
import org.dev.otte.user.command.domain.SocialProvider

data class OAuth2LoginRequest(
    @Schema(description = "소셜 프로바이더", example = "kakao")
    val provider: String,
    @Schema(description = "일회용 authorization_code", example = "one_time_authorization_code")
    val code: String
) {
    fun toCommand(): OAuth2LoginCommand {
        return OAuth2LoginCommand(SocialProvider.of(provider), code)
    }
}
