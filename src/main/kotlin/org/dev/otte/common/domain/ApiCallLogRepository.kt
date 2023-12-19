package org.dev.otte.common.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ApiCallLogRepository : JpaRepository<ApiCallLog, Long>