package org.dev.otte.movie.query.dto

import org.dev.otte.user.command.domain.Ott

data class MovieRecommendCondition(
    val userId: Long?,
    val ottList: List<Ott>,
    val feeling: String,
    val situation: String
)
