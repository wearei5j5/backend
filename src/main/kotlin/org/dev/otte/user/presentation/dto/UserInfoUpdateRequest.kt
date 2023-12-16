package org.dev.otte.user.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.dev.otte.user.command.application.dto.UserInfoUpdateCommand
import org.dev.otte.user.command.domain.Ott

data class UserInfoUpdateRequest(
    @Schema(description = "이름", example = "honggildong")
    val name: String,
    @Schema(description = "나이", example = "25")
    val age: Int,
    @Schema(description = "OTT 리스트")
    val ottList: List<Ott>
) {
    fun toCommand(userId: Long): UserInfoUpdateCommand {
        return UserInfoUpdateCommand(
            userId,
            name,
            age,
            ottList
        )
    }
}
