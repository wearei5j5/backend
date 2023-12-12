package org.dev.otte.chat.presentation.dto

import org.dev.otte.chat.command.domain.ChatSpeaker

data class ChatRequest(
    val speaker: ChatSpeaker,
    val sentence: String
)