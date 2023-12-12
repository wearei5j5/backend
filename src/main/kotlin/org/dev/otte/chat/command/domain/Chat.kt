package org.dev.otte.chat.command.domain

import jakarta.persistence.*
import org.dev.otte.common.domain.BaseEntity
import org.dev.otte.movie.command.domain.RecommendedMovie
import org.dev.otte.user.command.domain.User

@Entity
@Table(name = "chat")
class Chat(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_id")
    val user: User
) : BaseEntity() {

    @Column
    val title: String = "수첩"

    @OneToMany(mappedBy = "chat")
    private val _movies: MutableList<RecommendedMovie> = mutableListOf()
    val movies: List<RecommendedMovie>
        get() = _movies.toList()
}