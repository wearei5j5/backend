package org.dev.otte.user.query.dao.support

import org.dev.otte.user.command.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserQueryDaoSupport : JpaRepository<User, Long>