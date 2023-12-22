package org.dev.otte.movie.query.dao.support

import org.dev.otte.movie.command.domain.RecommendedMovieLog
import org.springframework.data.jpa.repository.JpaRepository

interface RecommendedMovieLogQueryDaoSupport : JpaRepository<RecommendedMovieLog, Long>