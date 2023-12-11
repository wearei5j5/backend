package org.dev.otte.movie.command.domain

import jakarta.persistence.*
import org.dev.otte.chat.presentation.command.domain.Chat
import org.dev.otte.common.domain.BaseEntity
import org.dev.otte.common.util.StringListConverter
import org.dev.otte.user.command.domain.User

@Entity
@Table(name = "recommended_movie")
class RecommendedMovie(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "chat_id")
    val chat: Chat,

    @Column
    val movieName: String,

    @Column
    @Convert(converter = StringListConverter::class)
    val keywords: List<String>,

    @Column
    val posterImageUrl: String?
) : BaseEntity()