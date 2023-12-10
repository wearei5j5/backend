package org.dev.otte.movie.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

data class MovieRecommendRequest(
    @Schema(description = "구독중인 OTT 서비스 목록", example = "[\"netflix\",\"tving\",\"disneyplus\"]")
    val ottList: List<String>,
    @Schema(description = "감정", example = "지금 기분이 우울해")
    val feeling: String,
    @Schema(description = "상황", example = "기분좋게 해줄 영화를 찾고 있어")
    val situation: String
)