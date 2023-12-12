package org.dev.otte.chat.query.dto

import org.dev.otte.movie.command.domain.RecommendedMovie

data class ChatListQueryResponse(
    val title: String,
    val movies: List<RecommendedMovieResponse>
)

data class RecommendedMovieResponse(
    val movieName: String,
    val keywords: List<String>,
    var posterImageUrl: String? = null
) {
    constructor(movie: RecommendedMovie) : this(
        movie.movieName,
        movie.keywords,
        movie.posterImageUrl
    )
}