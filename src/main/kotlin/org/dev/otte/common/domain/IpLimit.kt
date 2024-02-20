package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kotlin.math.max

private const val DAILY_CALL_LIMIT_COUNT = 3

@Entity
@Table(name = "ip_limit")
class IpLimit(
    @Column
    val ip: String
) : BaseEntity() {

    @Column
    var totalCallCount: Int = 0
        protected set

    @Column
    var remain: Int = DAILY_CALL_LIMIT_COUNT
        protected set

    fun call() {
        totalCallCount++
        remain = max(remain - 1, 0)
    }

    fun isRunOut(): Boolean {
        return remain == 0
    }

    fun reset() {
        remain = DAILY_CALL_LIMIT_COUNT
    }

    fun releaseLimit() {
        remain = Int.MAX_VALUE
    }
}