package org.dev.otte.movie.infra.tmdb.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("tmdb")
data class TmdbProperties(
    val accessToken: String
)