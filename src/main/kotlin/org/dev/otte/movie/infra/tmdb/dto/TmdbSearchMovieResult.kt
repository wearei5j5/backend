package org.dev.otte.movie.infra.tmdb.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class TmdbSearchMovieResult(
    val results: List<MovieResult>
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MovieResult(
    val posterPath: String?,
    val releaseDate: String?
)
