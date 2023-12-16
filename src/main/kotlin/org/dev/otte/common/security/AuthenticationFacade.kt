package org.dev.otte.common.security

import org.dev.otte.user.command.domain.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationFacade {
    fun getUserIdOrNull(): Long? {
        val user = SecurityContextHolder.getContext().authentication.principal as? User?
        return user?.id
    }
}