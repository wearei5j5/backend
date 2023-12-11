package org.dev.otte.auth.command.application

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.dev.otte.auth.config.JwtProperties
import org.dev.otte.user.command.domain.User
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    private val properties: JwtProperties
) {
    fun generateAccessToken(user: User): String {
        return generateToken(user, properties.accessExpiration)
    }

    fun generateRefreshToken(user: User): String {
        return generateToken(user, properties.refreshExpiration)
    }

    fun extractUserId(token: String): Long {
        return extractClaim(token, Claims::getSubject).toLong()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            extractClaim(token, Claims::getExpiration)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun generateToken(
        user: User,
        expiration: Long
    ): String {
        return Jwts
            .builder()
            .setSubject(user.id.toString())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver.invoke(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return try {
            Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ClaimJwtException) {
            throw e
        }
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(properties.secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}