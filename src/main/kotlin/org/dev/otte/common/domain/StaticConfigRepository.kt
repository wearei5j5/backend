package org.dev.otte.common.domain

import org.springframework.data.jpa.repository.JpaRepository

fun StaticConfigRepository.get(): StaticConfig = findAll().last()

interface StaticConfigRepository : JpaRepository<StaticConfig, Long>