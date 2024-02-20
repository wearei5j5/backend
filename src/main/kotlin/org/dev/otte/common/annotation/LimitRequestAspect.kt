package org.dev.otte.common.annotation

import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.dev.otte.common.domain.IpLimit
import org.dev.otte.common.domain.IpLimitRepository
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@Aspect
@Component
class LimitRequestAspect(
    private val ipLimitRepository: IpLimitRepository,
) {
    @Before("@annotation(LimitRequest)")
    fun aop() {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val requestIp = getRequestIpFrom(request)
        val ipLimit = ipLimitRepository.findByIp(requestIp)
            ?: IpLimit(requestIp)
        if (ipLimit.isRunOut()) {
            throw IllegalArgumentException("호출 횟수를 초과했습니다")
        }
        ipLimit.call()
        ipLimitRepository.save(ipLimit)
    }

    private fun getRequestIpFrom(request: HttpServletRequest): String =
        request.getHeader("X-Forwarded-For")
            ?.let { it.split(",")[0].trim() }
            ?: request.remoteAddr
}

