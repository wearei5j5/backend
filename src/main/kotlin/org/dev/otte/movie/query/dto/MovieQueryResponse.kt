package org.dev.otte.movie.query.dto

import org.dev.otte.movie.command.domain.Movie

data class MovieQueryResponse(
    val movieName: String,
    val keywords: List<String>,
    var posterImageUrl: String? = null
) {
    constructor(movie: Movie) : this(
        movie.movieName,
        movie.keywords,
        movie.posterImageUrl
    )
}