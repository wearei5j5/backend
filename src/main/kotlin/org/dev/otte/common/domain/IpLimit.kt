package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class IpLimit(
    @Column
    val ip: String?
) : BaseEntity() {
    fun call() {
        remain--
    }

    fun totalCountUp() {
        totalCallCount++
    }

    fun isRunOut(): Boolean {
        return remain < 1
    }

    var totalCallCount: Int = 0

    var remain: Int = 3
}