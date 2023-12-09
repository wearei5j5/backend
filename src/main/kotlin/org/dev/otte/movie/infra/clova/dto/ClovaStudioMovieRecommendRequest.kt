package org.dev.otte.movie.infra.clova.dto

data class ClovaStudioMovieRecommendRequest(
    val text: String,
    val start: String,
    val restart: String,
    val includeTokens: Boolean,
    val topP: Double,
    val topK: Int,
    val maxTokens: Int,
    val temperature: Double,
    val repeatPenalty: Double,
    val stopBefore: List<String>,
    val includeAiFilters: Boolean,
)
