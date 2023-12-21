package org.dev.otte.common.application

import org.dev.otte.common.domain.IpLimitRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class IpLimitResetScheduler(
    private val ipLimitRepository: IpLimitRepository
) {
    companion object {
        const val EVERYDAY_MIDNIGHT = "0 0 0 * * ?"
    }

    @Scheduled(cron = EVERYDAY_MIDNIGHT, zone = "GMT+09:00")
    @Transactional
    fun reset() {
        ipLimitRepository.findAll().map { it.reset() }
    }
}