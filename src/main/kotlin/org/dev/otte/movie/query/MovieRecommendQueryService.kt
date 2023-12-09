package org.dev.otte.movie.query

import org.dev.otte.movie.infra.clova.client.ClovaStudioMovieRecommendClient
import org.dev.otte.movie.query.dao.ClovaStudioEngineSettingDao
import org.dev.otte.movie.query.dto.MovieRecommendQueryResponse
import org.springframework.stereotype.Service

@Service
class MovieRecommendQueryService(
    private val client: ClovaStudioMovieRecommendClient,
    private val clovaStudioEngineSettingDao: ClovaStudioEngineSettingDao
) {
    fun recommend(ott: String, feeling: String, situation: String): MovieRecommendQueryResponse {
        val engineSetting = clovaStudioEngineSettingDao.findClovaStudioEngineSetting()
        val movieRecommendRequest = engineSetting.toRequest(ott, feeling, situation)
        val rawRecommendResult = client.fetchMovieRecommend(movieRecommendRequest).result.outputText
        return parseMovieRecommendationString(rawRecommendResult)
            ?: throw IllegalArgumentException("i5j5 does not operate normally.")
    }

    private fun parseMovieRecommendationString(input: String): MovieRecommendQueryResponse? {
        val regex = """키워드: (.+?)\n영화: (.+?)\n###""".toRegex()

        val matchResult = regex.find(input)

        return if (matchResult != null) {
            val keywords = matchResult.groupValues[1].split(", ").toList()
            val movieName = matchResult.groupValues[2]
            MovieRecommendQueryResponse(keywords, movieName)
        } else {
            null
        }
    }
}

