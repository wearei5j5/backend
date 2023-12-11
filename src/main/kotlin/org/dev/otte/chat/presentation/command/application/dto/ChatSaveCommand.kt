package org.dev.otte.chat.presentation.command.application.dto

import org.dev.otte.chat.presentation.dto.ChatRequest

data class ChatSaveCommand(
    val userId: Long,
    val chat: List<ChatRequest>,
    val movieRecommendationCommand: MovieRecommendationCommand
)