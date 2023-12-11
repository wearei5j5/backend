package org.dev.otte.chat.presentation.dto

import org.dev.otte.chat.presentation.command.application.dto.ChatSaveCommand

data class ChatSaveRequest(
    val chat: List<ChatRequest>,
    val movieRecommendResult: MovieRecommendResult
) {
    fun toCommand(userId: Long): ChatSaveCommand {
        return ChatSaveCommand(
            userId,
            chat,
            movieRecommendResult.toCommand()
        )
    }
}



