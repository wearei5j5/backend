package org.dev.otte.auth.command.domain

import org.dev.otte.user.command.domain.SocialProvider
import org.springframework.stereotype.Component

@Component
class OAuth2AuthCodeUrlProviderContext(
    strategies: Set<OAuth2AuthCodeUrlProviderStrategy>
) {
    private val strategies: Map<SocialProvider, OAuth2AuthCodeUrlProviderStrategy> =
        strategies.associateBy { it.support }

    fun getRedirectUrl(provider: SocialProvider, state: String?): String {
        return strategies
            .getValue(provider)
            .provide(state)
    }
}

