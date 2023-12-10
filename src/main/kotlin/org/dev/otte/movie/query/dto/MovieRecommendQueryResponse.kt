package org.dev.otte.movie.query.dto

data class MovieRecommendQueryResponse(
    val movieName: String,
    val keywords: List<String>
) {
    var posterImageUrl: String? = null
}

