package org.dev.otte.movie.query

import org.dev.otte.movie.command.domain.MovieRecommendedEvent
import org.dev.otte.movie.infra.clova.client.ClovaStudioMovieRecommendClient
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendRequest
import org.dev.otte.movie.infra.tmdb.client.TmdbMovieSearchClient
import org.dev.otte.movie.query.dao.ClovaStudioEngineSettingDao
import org.dev.otte.movie.query.dto.MovieRecommendQueryResponse
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

@Service
@Transactional(readOnly = true)
class MovieRecommendQueryService(
    private val clovaStudioMovieRecommendClient: ClovaStudioMovieRecommendClient,
    private val clovaStudioEngineSettingDao: ClovaStudioEngineSettingDao,
    private val tmdbMovieSearchClient: TmdbMovieSearchClient,
    private val publisher: ApplicationEventPublisher
) {
    fun recommend(ottList: List<String>, feeling: String, situation: String): List<MovieRecommendQueryResponse> {
        val engineSetting = clovaStudioEngineSettingDao.findClovaStudioEngineSetting()
        val movieRecommendRequest = engineSetting.toRequest(ottList.joinToString(","), feeling, situation)

        val movieNameSet = mutableSetOf<String>()
        val movieRecommendQueryResponseList: MutableList<MovieRecommendQueryResponse> = mutableListOf()
        while (movieNameSet.size < 3) {
            Thread.sleep(1000)
            val movieRecommendQueryResponse = movieRecommendQueryResponse(movieRecommendRequest)
            if (!movieNameSet.contains(movieRecommendQueryResponse.movieName)) {
                movieNameSet.add(movieRecommendQueryResponse.movieName)
                movieRecommendQueryResponseList.add(movieRecommendQueryResponse)
                publisher.publishEvent(MovieRecommendedEvent(movieRecommendQueryResponse))
            }
        }

        return movieRecommendQueryResponseList.toList()
    }

    private fun movieRecommendQueryResponse(movieRecommendRequest: ClovaStudioMovieRecommendRequest): MovieRecommendQueryResponse {
        val rawRecommendResult =
            clovaStudioMovieRecommendClient.fetchMovieRecommend(movieRecommendRequest).result.outputText
        val movieRecommendQueryResponse = parseMovieRecommendationString(rawRecommendResult)
            ?.apply { posterImageUrl = getPosterImageUrl(movieName) }
            ?: throw IllegalArgumentException("i5j5 does not operate normally.")
        return movieRecommendQueryResponse
    }

    private fun parseMovieRecommendationString(input: String): MovieRecommendQueryResponse? {
        val lines = input.lines()
        val movieName = lines.firstOrNull { it.startsWith("영화:") }?.substringAfter(":")?.trim()
        val keywordLine = lines.firstOrNull { it.startsWith("키워드:") }?.substringAfter(":")?.trim() ?: ""
        val keywords = keywordLine.split(",").map { it.trim() }.map { "#$it" }

        if (movieName == null) {
            return null
        }
        return MovieRecommendQueryResponse(movieName, keywords)
    }

    private fun getPosterImageUrl(movieName: String): String? {
        val posterPath = getMaxVotedMoviePosterPath(movieName)
        return if (posterPath != null) {
            TMDB_POSTER_BASE_URL + posterPath
        } else {
            null
        }
    }

    private fun getMaxVotedMoviePosterPath(movieName: String): String? {
        return tmdbMovieSearchClient.searchMovie(movieName)
            .results
            .firstOrNull()
            ?.posterPath
    }
}

