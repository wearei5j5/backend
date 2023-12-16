package org.dev.otte.movie.query.dto

import org.dev.otte.movie.command.domain.Movie

data class MovieQueryResponse(
    val movieId: Long,
    val movieName: String,
    val keywords: List<String>,
    val posterImageUrl: String?,
    val releaseDate: String?
) {
    constructor(movie: Movie) : this(
        movie.id,
        movie.movieName,
        movie.keywords,
        movie.posterImageUrl,
        movie.releaseDate
    )
}