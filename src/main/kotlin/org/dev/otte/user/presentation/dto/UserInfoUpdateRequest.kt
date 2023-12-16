package org.dev.otte.user.presentation.dto

import org.dev.otte.user.command.application.dto.UserInfoUpdateCommand
import org.dev.otte.user.command.domain.Ott

data class UserInfoUpdateRequest(
    val name: String,
    val age: Int,
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
