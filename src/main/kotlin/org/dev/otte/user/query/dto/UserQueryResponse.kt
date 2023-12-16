package org.dev.otte.user.query.dto

import org.dev.otte.user.command.domain.Ott

data class UserQueryResponse(
    val name: String,
    val age: Int,
    val ottList: List<Ott>
)
