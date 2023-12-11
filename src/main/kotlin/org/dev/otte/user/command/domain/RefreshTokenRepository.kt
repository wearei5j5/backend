package org.dev.otte.user.command.domain

import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {
    fun findByJwt(jwt: String): RefreshToken?
    fun deleteAllByUserId(userId: Long)
}