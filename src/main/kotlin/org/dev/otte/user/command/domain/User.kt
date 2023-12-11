package org.dev.otte.user.command.domain

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import org.dev.otte.user.command.domain.UserState.VALID
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Entity
@Table(name = "user")
class User(
    @Column
    val socialUid: String,

    @Column
    @Enumerated(STRING)
    val socialProvider: SocialProvider,
    id: Long = 0L
) {

    @Column
    @Enumerated(STRING)
    val state: UserState = VALID

    @Column
    @Enumerated(STRING)
    val role: Role = Role.BASIC

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    val authorities: Collection<GrantedAuthority>
        get() = listOf(SimpleGrantedAuthority("ROLE_${role.name}"))

    fun resign() {
        // TODO: 정보 제거
    }

}