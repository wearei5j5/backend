package org.dev.otte.chat.command.domain

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import org.dev.otte.common.domain.BaseEntity

@Entity
@Table(name = "chat_sentence")
class ChatSentence(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "chat_id")
    val chat: Chat,

    @Column
    @Enumerated(STRING)
    val speaker: ChatSpeaker,

    @Column
    val sentence: String
) : BaseEntity()