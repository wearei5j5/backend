package org.dev.otte.chat.command.application.dto

data class MovieRecommendationCommand(
    val movieName: String,
    val keywords: List<String>,
    var posterImageUrl: String? = null
)
