package org.dev.otte.common.domain

import org.springframework.data.jpa.repository.JpaRepository

interface IpLimitRepository : JpaRepository<IpLimit, Long> {
    fun findByIp(ip: String): IpLimit?
}