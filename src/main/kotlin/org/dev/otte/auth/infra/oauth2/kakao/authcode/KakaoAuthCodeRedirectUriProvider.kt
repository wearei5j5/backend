package org.dev.otte.auth.infra.oauth2.kakao.authcode

import org.dev.otte.auth.command.domain.OAuth2AuthCodeUrlProviderStrategy
import org.dev.otte.auth.infra.oauth2.kakao.KakaoOAuth2Properties
import org.dev.otte.user.command.domain.SocialProvider
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class KakaoAuthCodeRedirectUriProvider(
    private val properties: KakaoOAuth2Properties
) : OAuth2AuthCodeUrlProviderStrategy {
    override val support: SocialProvider
        get() = SocialProvider.KAKAO

    override fun provide(state: String?): String {
        return UriComponentsBuilder
            .fromUriString("https://kauth.kakao.com/oauth/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", properties.clientId)
            .queryParam("redirect_uri", properties.redirectUri)
            .queryParam("scope", properties.scope.joinToString(","))
            .queryParam("state", state)
            .toUriString()
    }
}