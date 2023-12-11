package org.dev.otte.user.command.domain

enum class SocialProvider {
    KAKAO
    ;

    companion object {
        fun of(lowerCase: String): SocialProvider {
            return entries.find { it.name == lowerCase.uppercase() }
                ?: throw IllegalArgumentException("Not supported Provider")
        }
    }
}