package org.dev.otte.auth.command.application.dto

import org.dev.otte.user.command.domain.SocialProvider

data class OAuth2LoginCommand(
    val socialProvider: SocialProvider,
    val code: String
)
