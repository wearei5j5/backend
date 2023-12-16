package org.dev.otte.auth.command.domain

import org.dev.otte.user.command.domain.SocialProvider
import org.springframework.stereotype.Component

@Component
class OAuth2UserClientContext(
    strategies: Set<OAuth2UserClientStrategy>
) {
    private val strategies = strategies.associateBy { it.support }

    fun getOAuth2UserData(provider: SocialProvider, code: String): OAuth2UserData {
        return strategies
            .getValue(provider)
            .fetch(code)
    }
}