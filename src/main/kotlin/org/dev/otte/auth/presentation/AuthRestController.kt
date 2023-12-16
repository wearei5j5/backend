package org.dev.otte.auth.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.auth.command.application.AuthService
import org.dev.otte.auth.command.application.dto.TokenResponse
import org.dev.otte.auth.presentation.dto.TokenRefreshRequest
import org.dev.otte.common.presentation.dto.DataResult
import org.dev.otte.user.command.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/auth")
class AuthRestController(
    private val authService: AuthService
) {
    @PostMapping("/refresh")
    @Operation(summary = "Get access-token through refresh-token")
    fun generateTokenByRefreshToken(@RequestBody request: TokenRefreshRequest): ResponseEntity<DataResult<TokenResponse>> {
        return ResponseEntity.ok(DataResult(authService.generateTokenByRefreshToken(request.refreshToken)))
    }

    @PostMapping("/logout")
    @Operation(summary = "Delete refresh-token")
    fun logout(@AuthenticationPrincipal user: User): ResponseEntity<Any> {
        authService.logout(user.id)
        return ResponseEntity.ok().build()
    }
}