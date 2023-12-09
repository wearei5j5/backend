package org.dev.otte.movie.infra.clova.dto

data class ClovaStudioMovieRecommendResult(
    val result: ClovaStudioResult
)

data class ClovaStudioResult(
    val outputText: String
)