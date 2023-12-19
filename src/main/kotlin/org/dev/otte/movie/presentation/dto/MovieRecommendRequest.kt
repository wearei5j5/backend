package org.dev.otte.movie.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.dev.otte.movie.query.dto.MovieRecommendCondition
import org.dev.otte.user.command.domain.Ott

data class MovieRecommendRequest(
    @Schema(description = "구독중인 OTT 서비스 목록", example = "[\"NETFLIX\",\"TVING\",\"WATCHA\"]")
    @field:NotEmpty(message = "Input ott list can not be empty")
    var ottList: List<Ott>,
    @Schema(description = "감정", example = "지금 기분이 우울해")
    @field:Size(min = 1, max = 30)
    val feeling: String,
    @Schema(description = "상황", example = "기분좋게 해줄 영화를 찾고 있어")
    @field:Size(min = 1, max = 30)
    val situation: String
) {
    fun toCondition(userId: Long?): MovieRecommendCondition {
        if (Ott.NONE in ottList) {
            ottList = Ott.entries.filterNot { it == Ott.NONE }
        }
        return MovieRecommendCondition(
            userId,
            ottList,
            feeling,
            situation
        )
    }
}
