package org.dev.otte.movie.command.application

import org.dev.otte.movie.command.application.dto.MovieSaveCommand
import org.dev.otte.movie.command.application.dto.MovieSaveCommandResponse
import org.dev.otte.movie.command.domain.Movie
import org.dev.otte.movie.command.domain.MovieRepository
import org.dev.otte.user.command.domain.UserRepository
import org.dev.otte.user.command.domain.getOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MovieService(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository
) {
    fun save(command: MovieSaveCommand): MovieSaveCommandResponse {
        val user = userRepository.getOrThrow(command.userId)
        val movie = movieRepository.save(
            Movie(
                user,
                command.movieName,
                command.keywords,
                command.posterImageUrl,
                command.releaseDate
            )
        )
        return MovieSaveCommandResponse(movie.id)
    }

    fun delete(movieId: Long) {
        movieRepository.deleteById(movieId)
    }
}