package org.dev.otte.auth.infra.oauth2.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.dev.otte.auth.command.domain.OAuth2UserData
import org.dev.otte.user.command.domain.SocialProvider
import java.time.LocalDateTime

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoUserResponse(
    val id: Long,
    val connectedAt: LocalDateTime,
    val properties: KakaoUserProperties,
    val kakaoAccount: KakaoAccount,
) {
    fun toOAuth2UserData(): OAuth2UserData {
        return OAuth2UserData(SocialProvider.KAKAO, id.toString(), properties.profileImage)
    }
}

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoUserProperties(
    val nickname: String,
    val profileImage: String?
)

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoAccount(
    val profileNicknameNeedsAgreement: Boolean,
    val profile: KakaoAccountProfile,
)

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoAccountProfile(
    val nickname: String
)