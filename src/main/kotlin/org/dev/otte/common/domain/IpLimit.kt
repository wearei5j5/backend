package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class IpLimit(
    @Column
    val ip: String
) : BaseEntity() {

    @Column
    var totalCallCount: Int = 0
        protected set

    @Column
    var remain: Int = 3
        protected set

    fun call() {
        remain--
    }

    fun totalCountUp() {
        totalCallCount++
    }

    fun isRunOut(): Boolean {
        return remain < 1
    }
}