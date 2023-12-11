package org.dev.otte.auth.command.domain

import org.dev.otte.user.command.domain.SocialProvider

interface OAuth2AuthCodeUrlProviderStrategy {
    val support: SocialProvider
    fun provide(state: String?): String
}