package org.dev.otte.movie.command.application

import org.dev.otte.movie.command.domain.MovieRecommendedEvent
import org.dev.otte.movie.command.domain.RecommendedMovie
import org.dev.otte.movie.command.domain.RecommendedMovieRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Service
class MovieEventListener(
    private val recommendedMovieRepository: RecommendedMovieRepository
) {
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun listen(event: MovieRecommendedEvent) {
        recommendedMovieRepository.save(
            RecommendedMovie(
                event.movieRecommendQueryResponse.movieName,
                event.movieRecommendQueryResponse.keywords,
                event.movieRecommendQueryResponse.posterImageUrl
            )
        )
    }
}