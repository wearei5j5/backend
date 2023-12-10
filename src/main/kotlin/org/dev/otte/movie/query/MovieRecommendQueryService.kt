package org.dev.otte.movie.query

import org.dev.otte.movie.infra.clova.client.ClovaStudioMovieRecommendClient
import org.dev.otte.movie.infra.tmdb.client.TmdbMovieSearchClient
import org.dev.otte.movie.query.dao.ClovaStudioEngineSettingDao
import org.dev.otte.movie.query.dto.MovieRecommendQueryResponse
import org.springframework.stereotype.Service

private const val TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

@Service
class MovieRecommendQueryService(
    private val clovaStudioMovieRecommendClient: ClovaStudioMovieRecommendClient,
    private val clovaStudioEngineSettingDao: ClovaStudioEngineSettingDao,
    private val tmdbMovieSearchClient: TmdbMovieSearchClient
) {
    fun recommend(ottList: List<String>, feeling: String, situation: String): List<MovieRecommendQueryResponse> {
        val engineSetting = clovaStudioEngineSettingDao.findClovaStudioEngineSetting()
        val movieRecommendRequest = engineSetting.toRequest(ott, feeling, situation)
        val rawRecommendResult =
            clovaStudioMovieRecommendClient.fetchMovieRecommend(movieRecommendRequest).result.outputText
        val movieRecommendQueryResponse = parseMovieRecommendationString(rawRecommendResult)
            ?: throw IllegalArgumentException("i5j5 does not operate normally.")
        val posterImageUrl = getPosterImageUrl(movieRecommendQueryResponse.movieName)
        return movieRecommendQueryResponse
            .apply { this.posterImageUrl = posterImageUrl }

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

