package org.dev.otte.user.query

import org.dev.otte.user.query.dao.UserQueryDao
import org.dev.otte.user.query.dto.UserQueryResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class UserQueryService(
    private val userQueryDao: UserQueryDao
) {
    fun get(userId: Long): UserQueryResponse {
        return userQueryDao.get(userId)
    }
}