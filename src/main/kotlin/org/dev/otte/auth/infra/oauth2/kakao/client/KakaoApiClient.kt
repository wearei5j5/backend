package org.dev.otte.auth.infra.oauth2.kakao.client

import org.dev.otte.auth.infra.oauth2.kakao.dto.KakaoToken
import org.dev.otte.auth.infra.oauth2.kakao.dto.KakaoUserResponse
import org.springframework.http.HttpHeaders
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.PostExchange

interface KakaoApiClient {
    @PostExchange("https://kauth.kakao.com/oauth/token")
    fun fetchToken(@RequestParam params: MultiValueMap<String, String>): KakaoToken

    @GetExchange("https://kapi.kakao.com/v2/user/me")
    fun fetchUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) accessToken: String): KakaoUserResponse
}