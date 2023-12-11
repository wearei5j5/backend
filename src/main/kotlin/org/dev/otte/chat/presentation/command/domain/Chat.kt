package org.dev.otte.chat.presentation.command.domain

import jakarta.persistence.*
import org.dev.otte.common.domain.BaseEntity
import org.dev.otte.user.command.domain.User

@Entity
@Table(name = "chat")
class Chat(
    @ManyToOne
    @JoinTable(name = "user_id")
    val user: User
) : BaseEntity() {

    @Column
    val title: String = "수첩"
}