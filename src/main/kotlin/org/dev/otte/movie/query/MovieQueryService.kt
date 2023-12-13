package org.dev.otte.movie.query

import org.dev.otte.movie.query.dao.MovieQueryDao
import org.dev.otte.movie.query.dto.MovieQueryResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MovieQueryService(
    private val movieQueryDao: MovieQueryDao
) {
    fun findAll(userId: Long): List<MovieQueryResponse> {
        return movieQueryDao.findAll(userId)
    }
}