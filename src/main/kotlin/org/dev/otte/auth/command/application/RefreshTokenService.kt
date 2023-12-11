package org.dev.otte.auth.command.application

import org.dev.otte.user.command.domain.RefreshToken
import org.dev.otte.user.command.domain.RefreshTokenRepository
import org.dev.otte.user.command.domain.UserRepository
import org.dev.otte.user.command.domain.getOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
) {
    fun rotate(userId: Long, jwt: String) {
        val user = userRepository.getOrThrow(userId)
        refreshTokenRepository.deleteAllByUserId(userId)
        refreshTokenRepository.save(RefreshToken(user, jwt))
    }

    fun findByJwt(jwt: String): RefreshToken {
        return refreshTokenRepository.findByJwt(jwt)
            ?: throw IllegalArgumentException("Invalid refresh token")
    }

    fun deleteAll(userId: Long) {
        refreshTokenRepository.deleteAllByUserId(userId)
    }
}