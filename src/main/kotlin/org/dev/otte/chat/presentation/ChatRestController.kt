package org.dev.otte.chat.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.chat.command.application.ChatService
import org.dev.otte.chat.presentation.dto.ChatSaveRequest
import org.dev.otte.chat.query.ChatQueryService
import org.dev.otte.chat.query.dto.ChatListQueryResponse
import org.dev.otte.user.command.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "Chat")
@RestController
@RequestMapping("/api/v1/chat")
class ChatRestController(
    private val chatService: ChatService,
    private val chatQueryService: ChatQueryService
) {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    @Operation(summary = "Archive user chat & recommended movie")
    fun save(
        @AuthenticationPrincipal user: User,
        @RequestBody request: ChatSaveRequest
    ): ResponseEntity<Any> {
        chatService.save(request.toCommand(user.id))
        return ResponseEntity.ok().build()
    }

    @GetMapping
    @Operation(summary = "Find All User's Char")
    fun get(@AuthenticationPrincipal user: User): ResponseEntity<List<ChatListQueryResponse>> {
        return ResponseEntity.ok(chatQueryService.findAll(user.id))
    }
}