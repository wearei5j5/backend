package org.dev.otte.movie.query.dto

data class MovieRecommendQueryResponse(
    val keywords: List<String>,
    val movieName: String
)
