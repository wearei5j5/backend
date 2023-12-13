package org.dev.otte.movie.command.application

import org.dev.otte.movie.command.domain.MovieRecommendedEvent
import org.dev.otte.movie.command.domain.RecommendedMovieLog
import org.dev.otte.movie.command.domain.RecommendedMovieLogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Service
class MovieEventListener(
    private val recommendedMovieLogRepository: RecommendedMovieLogRepository
) {
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun listen(event: MovieRecommendedEvent) {
        recommendedMovieLogRepository.save(
            RecommendedMovieLog(
                event.movieRecommendQueryResponse.movieName,
                event.movieRecommendQueryResponse.keywords,
                event.movieRecommendQueryResponse.posterImageUrl,
                event.movieRecommendQueryResponse.releaseDate
            )
        )
    }
}