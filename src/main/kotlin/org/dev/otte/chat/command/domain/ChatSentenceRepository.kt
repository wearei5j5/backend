package org.dev.otte.chat.command.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ChatSentenceRepository : JpaRepository<ChatSentence, Long>