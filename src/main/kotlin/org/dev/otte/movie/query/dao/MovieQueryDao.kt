package org.dev.otte.movie.query.dao

import org.dev.otte.common.annotation.Dao
import org.dev.otte.movie.query.dao.support.MovieQueryDaoSupport
import org.dev.otte.movie.query.dto.MovieQueryResponse

@Dao
class MovieQueryDao(
    private val movieQueryDaoSupport: MovieQueryDaoSupport
) {
    fun findAll(userId: Long): List<MovieQueryResponse> {
        return movieQueryDaoSupport
            .findAllByUserId(userId)
            .map { MovieQueryResponse(it) }
    }
}