package org.dev.otte.movie.presentation.dto

import org.dev.otte.movie.command.application.dto.MovieSaveCommand

data class MovieSaveRequest(
    val movieName: String,
    val keywords: List<String>,
    val posterImageUrl: String? = null,
    val releaseDate: String? = null
) {
    fun toCommand(userId: Long): MovieSaveCommand {
        return MovieSaveCommand(
            userId,
            movieName,
            keywords,
            posterImageUrl,
            releaseDate
        )
    }
}