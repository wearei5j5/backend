package org.dev.otte.movie.infra.tmdb.client

import org.dev.otte.movie.infra.tmdb.config.TmdbProperties
import org.dev.otte.movie.infra.tmdb.dto.TmdbSearchMovieResult
import org.springframework.stereotype.Component

@Component
class TmdbMovieSearchClient(
    private val tmdbApiClient: TmdbApiClient,
    private val tmdbProperties: TmdbProperties
) {
    fun searchMovie(movieName: String): TmdbSearchMovieResult {
        return tmdbApiClient.searchMovie(bearer(tmdbProperties.accessToken), movieName)
    }

    private fun bearer(accessToken: String): String {
        return "Bearer $accessToken"
    }
}