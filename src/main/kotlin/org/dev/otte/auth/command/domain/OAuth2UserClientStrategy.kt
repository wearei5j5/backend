package org.dev.otte.auth.command.domain

import org.dev.otte.user.command.domain.SocialProvider

interface OAuth2UserClientStrategy {
    val support: SocialProvider
    fun fetch(code: String): OAuth2UserData
}