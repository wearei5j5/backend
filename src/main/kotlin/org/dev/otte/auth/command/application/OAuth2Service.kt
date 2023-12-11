package org.dev.otte.auth.command.application

import org.dev.otte.auth.command.application.dto.OAuth2LoginCommand
import org.dev.otte.auth.command.application.dto.TokenResponse
import org.dev.otte.auth.command.domain.OAuth2AuthCodeUrlProviderContext
import org.dev.otte.auth.command.domain.OAuth2UserClientContext
import org.dev.otte.auth.command.domain.OAuth2UserData
import org.dev.otte.user.command.domain.SocialProvider
import org.dev.otte.user.command.domain.User
import org.dev.otte.user.command.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuth2Service(
    private val oAuth2UserClientContext: OAuth2UserClientContext,
    private val oAuth2AuthCodeUrlProviderContext: OAuth2AuthCodeUrlProviderContext,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val refreshTokenService: RefreshTokenService
) {
    @Transactional
    fun login(command: OAuth2LoginCommand): TokenResponse {
        val oAuth2UserData = oAuth2UserClientContext.getOAuth2UserData(command.socialProvider, command.code)
        val user =
            userRepository.findBySocialUidAndSocialProvider(oAuth2UserData.socialUid, oAuth2UserData.socialProvider)
                ?: createUser(oAuth2UserData)
        return tokenResponse(user)
    }

    fun getRedirectUrl(socialProvider: SocialProvider, state: String?): String {
        return oAuth2AuthCodeUrlProviderContext.getRedirectUrl(socialProvider, state)
    }

    private fun createUser(data: OAuth2UserData): User {
        return userRepository.save(User(data.socialUid, data.socialProvider))
    }

    private fun tokenResponse(user: User): TokenResponse {
        val accessToken = jwtProvider.generateAccessToken(user)
        val refreshToken = jwtProvider.generateRefreshToken(user)
        refreshTokenService.rotate(user.id, refreshToken)
        return TokenResponse(accessToken, refreshToken)
    }
}