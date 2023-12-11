package org.dev.otte.auth.infra.oauth2.config

import org.dev.otte.auth.infra.oauth2.kakao.client.KakaoApiClient
import org.dev.otte.common.infra.AbstractHttpInterfaceConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthHttpInterfaceConfig : AbstractHttpInterfaceConfig() {
    @Bean
    fun kakaoApiClient(): KakaoApiClient {
        return createHttpInterface(KakaoApiClient::class.java)
    }
}