package org.dev.otte.chat.query.dao.support

import org.dev.otte.chat.command.domain.Chat
import org.springframework.data.jpa.repository.JpaRepository

interface ChatQueryDaoSupport : JpaRepository<Chat, Long> {
    fun findAllByUserIdOrderByIdDesc(userId: Long): List<Chat>
}