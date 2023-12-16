package org.dev.otte.user.command.domain

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import org.dev.otte.common.domain.BaseEntity
import org.dev.otte.user.command.domain.UserState.VALID
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Entity
@Table(name = "user")
class User(
    @Column
    val socialUid: String,

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(STRING)
    val socialProvider: SocialProvider,

    @Column
    var profileImageUrl: String? = null
) : BaseEntity() {

    @Column
    var name: String = ""
        protected set

    @Column
    var age: Int = 0
        protected set

    @Column
    @Convert(converter = OttConverter::class)
    var ottList: List<Ott> = emptyList()
        protected set

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(STRING)
    val state: UserState = VALID

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(STRING)
    val role: Role = Role.BASIC

    val authorities: Collection<GrantedAuthority>
        get() = listOf(SimpleGrantedAuthority("ROLE_${role.name}"))

    fun resign() {
        // TODO: 정보 제거
    }

    fun update(
        name: String,
        age: Int,
        ottList: List<Ott>
    ) {
        this.name = name
        this.age = age
        this.ottList = ottList
    }
}