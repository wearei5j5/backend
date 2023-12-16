package org.dev.otte.user.command.application.dto

import org.dev.otte.user.command.domain.Ott

data class UserInfoUpdateCommand(
    val userId: Long,
    val name: String,
    val age: Int,
    val ottList: List<Ott>
)
