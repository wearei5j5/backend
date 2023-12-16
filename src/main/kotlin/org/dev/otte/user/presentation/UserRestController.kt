package org.dev.otte.user.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.common.presentation.dto.DataResult
import org.dev.otte.user.command.application.UserService
import org.dev.otte.user.command.domain.User
import org.dev.otte.user.presentation.dto.UserInfoUpdateRequest
import org.dev.otte.user.query.UserQueryService
import org.dev.otte.user.query.dto.UserQueryResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/user")
class UserRestController(
    private val userQueryService: UserQueryService,
    private val userService: UserService
) {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    @Operation(summary = "Get User Information")
    fun get(@AuthenticationPrincipal user: User): ResponseEntity<DataResult<UserQueryResponse>> {
        return ResponseEntity.ok(DataResult(userQueryService.get(user.id)))
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    @Operation(summary = "Update User Information")
    fun update(
        @AuthenticationPrincipal user: User,
        @RequestBody request: UserInfoUpdateRequest
    ): ResponseEntity<Any> {
        userService.update(request.toCommand(user.id))
        return ResponseEntity.ok().build()
    }
}