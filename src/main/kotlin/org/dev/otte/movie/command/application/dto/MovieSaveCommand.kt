package org.dev.otte.movie.command.application.dto

data class MovieSaveCommand(
    val userId: Long,
    val movieName: String,
    val keywords: List<String>,
    val posterImageUrl: String? = null,
    val releaseDate: String? = null
)