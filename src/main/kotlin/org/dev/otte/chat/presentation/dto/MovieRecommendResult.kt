package org.dev.otte.chat.presentation.dto

import org.dev.otte.chat.presentation.command.application.dto.MovieRecommendationCommand

data class MovieRecommendResult(
    val movieName: String,
    val keywords: List<String>,
    var posterImageUrl: String? = null
) {
    fun toCommand(): MovieRecommendationCommand {
        return MovieRecommendationCommand(
            movieName,
            keywords,
            posterImageUrl
        )
    }
}