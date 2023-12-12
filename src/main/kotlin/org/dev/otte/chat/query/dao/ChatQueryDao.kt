package org.dev.otte.chat.query.dao

import org.dev.otte.chat.command.domain.Chat
import org.dev.otte.chat.query.dao.support.ChatQueryDaoSupport
import org.springframework.stereotype.Component

@Component
class ChatQueryDao(
    private val chatQueryDaoSupport: ChatQueryDaoSupport
) {
    fun findAll(userId: Long): List<Chat> {
        return chatQueryDaoSupport.findAllByUserIdOrderByIdDesc(userId)
    }
}