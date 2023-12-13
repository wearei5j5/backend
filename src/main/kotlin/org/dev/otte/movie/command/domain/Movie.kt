package org.dev.otte.movie.command.domain

import jakarta.persistence.*
import org.dev.otte.common.domain.BaseEntity
import org.dev.otte.common.util.StringListConverter
import org.dev.otte.user.command.domain.User

@Entity
@Table(name = "movie")
class Movie(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column
    val movieName: String,

    @Column
    @Convert(converter = StringListConverter::class)
    val keywords: List<String>,

    @Column
    val posterImageUrl: String?,

    @Column
    val releaseDate: String?
) : BaseEntity()