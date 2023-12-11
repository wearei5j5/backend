package org.dev.otte.chat.presentation.command.application

import org.dev.otte.chat.presentation.command.application.dto.ChatSaveCommand
import org.dev.otte.chat.presentation.command.domain.Chat
import org.dev.otte.chat.presentation.command.domain.ChatRepository
import org.dev.otte.chat.presentation.command.domain.ChatSentence
import org.dev.otte.chat.presentation.command.domain.ChatSentenceRepository
import org.dev.otte.movie.command.domain.RecommendedMovie
import org.dev.otte.movie.command.domain.RecommendedMovieRepository
import org.dev.otte.user.command.domain.UserRepository
import org.dev.otte.user.command.domain.getOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ChatService(
    private val userRepository: UserRepository,
    private val recommendedMovieRepository: RecommendedMovieRepository,
    private val chatRepository: ChatRepository,
    private val chatSentenceRepository: ChatSentenceRepository
) {
    fun save(command: ChatSaveCommand) {
        val user = userRepository.getOrThrow(command.userId)
        val chat = chatRepository.save(Chat(user))
        recommendedMovieRepository.save(
            RecommendedMovie(
                user,
                chat,
                command.movieRecommendationCommand.movieName,
                command.movieRecommendationCommand.keywords,
                command.movieRecommendationCommand.posterImageUrl
            )
        )
        chatSentenceRepository.saveAll(command.chat.map { ChatSentence(chat, it.speaker, it.sentence) })
    }
}