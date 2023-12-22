package org.dev.otte.movie.query

import org.dev.otte.movie.command.domain.MovieRecommendedEvent
import org.dev.otte.movie.infra.clova.client.ClovaStudioMovieRecommendClient
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendRequest
import org.dev.otte.movie.infra.tmdb.client.TmdbMovieSearchClient
import org.dev.otte.movie.infra.tmdb.dto.MovieResult
import org.dev.otte.movie.query.dao.ClovaStudioEngineSettingDao
import org.dev.otte.movie.query.dao.MovieQueryDao
import org.dev.otte.movie.query.dao.RecommendedMovieLogQueryDao
import org.dev.otte.movie.query.dto.MovieQueryResponse
import org.dev.otte.movie.query.dto.MovieRecommendCondition
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
    private val publisher: ApplicationEventPublisher,
    private val movieQueryDao: MovieQueryDao,
    private val recommendedMovieLogQueryDao: RecommendedMovieLogQueryDao
) {
    fun recommend(
        condition: MovieRecommendCondition
    ): List<MovieRecommendQueryResponse> {
        return recommendedMovieLogQueryDao.findRandomThreeRecommendedMovie()
            .map {
                MovieRecommendQueryResponse(
                    it.movieName,
                    it.keywords
                ).apply {
                    this.posterImageUrl = it.posterImageUrl
                    this.releaseDate = it.releaseDate
                }
            }

        val engineSetting = clovaStudioEngineSettingDao.findClovaStudioEngineSetting()
        val movieRecommendRequest =
            engineSetting.toRequest(condition.ottList.joinToString(","), condition.feeling, condition.situation)

        val removeWhitespaceMovieNameSet = mutableSetOf<String>()
        val movieRecommendQueryResponseList: MutableList<MovieRecommendQueryResponse> = mutableListOf()

        var userMovies: List<MovieQueryResponse> = emptyList()

        if (condition.userId != null) {
            userMovies = movieQueryDao.findAll(condition.userId)
        }

        val removeWhitespaceUserMovieNames = userMovies.map { movie ->
            movie.movieName.filterNot { name -> name.isWhitespace() }
        }
        while (removeWhitespaceMovieNameSet.size < 3) {
            Thread.sleep(1000)
            val movieRecommendQueryResponse = movieRecommendQueryResponse(movieRecommendRequest)

            val removeWhitespaceMovieName = movieRecommendQueryResponse.movieName.filterNot { it.isWhitespace() }

            if (!removeWhitespaceMovieNameSet.contains(removeWhitespaceMovieName)) {
                if (removeWhitespaceUserMovieNames.contains(removeWhitespaceMovieName)) {
                    movieRecommendQueryResponse.isCollected = true
                }
                removeWhitespaceMovieNameSet.add(removeWhitespaceMovieName)
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
            ?: throw IllegalArgumentException("i5j5 does not operate normally.")
        val firstMovieInSearchResults = getFirstMovieInSearchResults(movieRecommendQueryResponse.movieName)
        return movieRecommendQueryResponse.apply {
            posterImageUrl = getPosterImageUrl(firstMovieInSearchResults?.posterPath)
            releaseDate = firstMovieInSearchResults?.releaseDate
        }
    }

    private fun parseMovieRecommendationString(input: String): MovieRecommendQueryResponse? {
        val lines = input.lines()
        val movieName = lines.firstOrNull { it.startsWith("영화:") }?.substringAfter(":")?.trim()
        val keywordLine = lines.firstOrNull { it.startsWith("키워드:") }?.substringAfter(":")?.trim() ?: ""
        val keywords = keywordLine.split(",").map { it.trim() }

        if (movieName == null) {
            return null
        }
        return MovieRecommendQueryResponse(movieName, keywords)
    }

    private fun getPosterImageUrl(posterPath: String?): String? {
        return if (posterPath != null) {
            TMDB_POSTER_BASE_URL + posterPath
        } else {
            null
        }
    }

    private fun getFirstMovieInSearchResults(movieName: String): MovieResult? {
        return tmdbMovieSearchClient.searchMovie(movieName)
            .results
            .firstOrNull()
    }
}

