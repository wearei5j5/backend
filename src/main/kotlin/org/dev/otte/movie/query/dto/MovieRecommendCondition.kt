package org.dev.otte.movie.query.dto

data class MovieRecommendCondition(
    val userId: Long?,
    val ottList: List<String>,
    val feeling: String,
    val situation: String
)
