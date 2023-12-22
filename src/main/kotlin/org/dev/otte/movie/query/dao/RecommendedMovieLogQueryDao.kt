package org.dev.otte.movie.query.dao

import org.dev.otte.common.annotation.Dao
import org.dev.otte.movie.command.domain.RecommendedMovieLog
import org.dev.otte.movie.query.dao.support.RecommendedMovieLogQueryDaoSupport

@Dao
class RecommendedMovieLogQueryDao(
    private val recommendedMovieLogQueryDaoSupport: RecommendedMovieLogQueryDaoSupport
) {
    fun findRandomThreeRecommendedMovie(): List<RecommendedMovieLog> {
        return recommendedMovieLogQueryDaoSupport.findAll()
            .filter { it.posterImageUrl != null && it.posterImageUrl!!.isNotBlank() }
            .shuffled()
            .take(3)
    }
}