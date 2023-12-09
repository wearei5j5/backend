package org.dev.otte.movie.infra.clova.config

import org.dev.otte.movie.infra.clova.client.ClovaApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class ClovaApiConfig {
    @Bean
    fun clovaApiClient(): ClovaApiClient {
        return createHttpInterface(ClovaApiClient::class.java)
    }

    private fun <T> createHttpInterface(clazz: Class<T>): T {
        val webClient = WebClient.create()
        val build = HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(webClient)).build()
        return build.createClient(clazz)
    }
}