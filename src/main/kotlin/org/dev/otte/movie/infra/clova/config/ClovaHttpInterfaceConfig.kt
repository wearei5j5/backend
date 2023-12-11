package org.dev.otte.movie.infra.clova.config

import org.dev.otte.common.infra.AbstractHttpInterfaceConfig
import org.dev.otte.movie.infra.clova.client.ClovaApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClovaHttpInterfaceConfig : AbstractHttpInterfaceConfig() {
    @Bean
    fun clovaApiClient(): ClovaApiClient {
        return createHttpInterface(ClovaApiClient::class.java)
    }
}