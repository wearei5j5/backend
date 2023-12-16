package org.dev.otte.user.command.application

import org.dev.otte.user.command.application.dto.UserInfoUpdateCommand
import org.dev.otte.user.command.domain.UserRepository
import org.dev.otte.user.command.domain.getOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    fun update(command: UserInfoUpdateCommand) {
        val user = userRepository.getOrThrow(command.userId)
        user.update(
            command.name,
            command.age,
            command.ottList
        )
    }
}