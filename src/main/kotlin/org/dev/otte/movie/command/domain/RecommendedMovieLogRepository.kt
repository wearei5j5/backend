package org.dev.otte.movie.command.domain

import org.springframework.data.jpa.repository.JpaRepository

interface RecommendedMovieLogRepository : JpaRepository<RecommendedMovieLog, Long>