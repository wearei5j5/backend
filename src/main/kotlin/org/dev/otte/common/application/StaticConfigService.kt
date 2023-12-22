package org.dev.otte.common.application

import org.dev.otte.common.domain.IpLimitRepository
import org.dev.otte.common.domain.StaticConfig
import org.dev.otte.common.domain.StaticConfigRepository
import org.dev.otte.common.domain.get
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StaticConfigService(
    private val staticConfigRepository: StaticConfigRepository,
    private val iplimiRepository: IpLimitRepository
) {
    fun changeClientBaseUrl(url: String) {
        val staticConfig = staticConfigRepository.get()
        staticConfig.changeClientBaseUrl(url)
    }

    fun get(): StaticConfig {
        val staticConfig = staticConfigRepository.get()
        return staticConfig
    }

    fun releaseIplimit(ip: String) {
        val ipLimit = iplimiRepository.findByIp(ip) ?: throw IllegalArgumentException("존재하지 않는 ip입니다")
        ipLimit.releaseLimit()
    }
}