package org.dev.otte.auth.command.domain

import org.dev.otte.user.command.domain.SocialProvider

data class OAuth2UserData(
    val socialProvider: SocialProvider,
    val socialUid: String
)