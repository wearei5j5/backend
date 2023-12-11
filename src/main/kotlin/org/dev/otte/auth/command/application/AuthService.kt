package org.dev.otte.auth.command.application

import org.dev.otte.auth.command.application.dto.TokenResponse
import org.dev.otte.user.command.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthService(
    private val refreshTokenService: RefreshTokenService,
    private val jwtProvider: JwtProvider
) {
    fun generateTokenByRefreshToken(refreshToken: String): TokenResponse {
        if (!jwtProvider.isValidToken(refreshToken)) {
            throw IllegalArgumentException()
        }
        val user = findUserByRefreshToken(refreshToken)

        return tokenResponse(user)
    }

    fun logout(userId: Long) {
        refreshTokenService.deleteAll(userId)
    }

    private fun findUserByRefreshToken(refreshToken: String): User {
        return refreshTokenService
            .findByJwt(refreshToken)
            .user
    }

    private fun tokenResponse(user: User): TokenResponse {
        val accessToken = jwtProvider.generateAccessToken(user)
        val refreshToken = jwtProvider.generateRefreshToken(user)
        refreshTokenService.rotate(user.id, refreshToken)
        return TokenResponse(accessToken, refreshToken)
    }
}