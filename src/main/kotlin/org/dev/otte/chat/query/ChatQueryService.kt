package org.dev.otte.chat.query

import org.dev.otte.chat.query.dao.ChatQueryDao
import org.dev.otte.chat.query.dto.ChatListQueryResponse
import org.dev.otte.chat.query.dto.RecommendedMovieResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChatQueryService(
    private val chatQueryDao: ChatQueryDao
) {
    fun findAll(userId: Long): List<ChatListQueryResponse> {
        return chatQueryDao.findAll(userId).map { chat ->
            ChatListQueryResponse(
                chat.title,
                chat.movies.map { movie ->
                    RecommendedMovieResponse(movie)
                }
            )
        }
    }
}