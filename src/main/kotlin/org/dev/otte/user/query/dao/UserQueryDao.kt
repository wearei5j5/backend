package org.dev.otte.user.query.dao

import org.dev.otte.common.annotation.Dao
import org.dev.otte.user.query.dao.support.UserQueryDaoSupport
import org.dev.otte.user.query.dto.UserQueryResponse
import org.springframework.data.repository.findByIdOrNull

@Dao
class UserQueryDao(
    private val userQueryDaoSupport: UserQueryDaoSupport
) {
    fun get(userId: Long): UserQueryResponse {
        val user = userQueryDaoSupport.findByIdOrNull(userId)
            ?: throw NoSuchElementException("Not found user")
        return UserQueryResponse(
            user.name,
            user.age,
            user.ottList
        )
    }
}