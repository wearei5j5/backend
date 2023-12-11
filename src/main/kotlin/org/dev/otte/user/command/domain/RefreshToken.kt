package org.dev.otte.user.command.domain

import jakarta.persistence.*
import org.dev.otte.common.domain.BaseEntity

@Entity
@Table(name = "refresh_token")
class RefreshToken(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column
    val jwt: String
) : BaseEntity()