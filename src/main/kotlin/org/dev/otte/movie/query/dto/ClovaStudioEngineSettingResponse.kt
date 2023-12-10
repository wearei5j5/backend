package org.dev.otte.movie.query.dto

import org.dev.otte.movie.command.domain.ClovaStudioEngineSetting
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendRequest

data class ClovaStudioEngineSettingResponse(
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
) {
    constructor(engineSetting: ClovaStudioEngineSetting) : this(
        engineSetting.text,
        engineSetting.start,
        engineSetting.restart,
        engineSetting.includeTokens,
        engineSetting.topP,
        engineSetting.topK,
        engineSetting.maxTokens,
        engineSetting.temperature,
        engineSetting.repeatPenalty,
        engineSetting.stopBefore,
        engineSetting.includeAiFilters,
    )

    fun toRequest(ottList: String, feeling: String, situation: String): ClovaStudioMovieRecommendRequest {
        val condition = """
            OTT목록: $ottList
            기분: $feeling
            상황: $situation
        """.trimIndent()
        return ClovaStudioMovieRecommendRequest(
            text + condition,
            start,
            restart,
            includeTokens,
            topP,
            topK,
            maxTokens,
            temperature,
            repeatPenalty,
            stopBefore,
            includeAiFilters
        )
    }
}
