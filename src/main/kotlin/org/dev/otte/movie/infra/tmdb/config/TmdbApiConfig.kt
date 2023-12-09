package org.dev.otte.movie.infra.tmdb.config

import org.dev.otte.common.infra.AbstractApiConfig
import org.dev.otte.movie.infra.tmdb.client.TmdbApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TmdbApiConfig : AbstractApiConfig() {
    @Bean
    fun tmdbApiClient(): TmdbApiClient {
        return createHttpInterface(TmdbApiClient::class.java)
    }
}