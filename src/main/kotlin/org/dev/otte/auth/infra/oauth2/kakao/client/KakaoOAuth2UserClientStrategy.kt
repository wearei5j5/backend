package org.dev.otte.auth.infra.oauth2.kakao.client

import org.dev.otte.auth.command.domain.OAuth2UserClientStrategy
import org.dev.otte.auth.command.domain.OAuth2UserData
import org.dev.otte.auth.infra.oauth2.kakao.KakaoOAuth2Properties
import org.dev.otte.auth.infra.oauth2.kakao.dto.KakaoUserResponse
import org.dev.otte.user.command.domain.SocialProvider
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@Component
class KakaoOAuth2UserClientStrategy(
    private val kakaoApiClient: KakaoApiClient,
    private val properties: KakaoOAuth2Properties
) : OAuth2UserClientStrategy {
    override val support: SocialProvider
        get() = SocialProvider.KAKAO

    override fun fetch(code: String): OAuth2UserData {
        val kakaotoken = kakaoApiClient.fetchToken(tokenRequestParams(code))
        val kakaoUserResponse: KakaoUserResponse = kakaoApiClient.fetchUser("Bearer ${kakaotoken.accessToken}")
        return kakaoUserResponse.toOAuth2UserData()
    }

    private fun tokenRequestParams(code: String): MultiValueMap<String, String> {
        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("grant_type", "authorization_code")
        params.add("client_id", properties.clientId)
        params.add("client_secret", properties.clientSecret)
        params.add("code", code)
        params.add("redirect_uri", properties.redirectUri)
        return params
    }
}