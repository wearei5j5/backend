package org.dev.otte.common.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.dev.otte.common.domain.ApiCallLog
import org.dev.otte.common.domain.ApiCallLogRepository
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class ApiCallLogFilter(
    private val apiCallLogRepository: ApiCallLogRepository
) : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val requestWrapper = ContentCachingRequestWrapper(request as HttpServletRequest)
        val responseWrapper = ContentCachingResponseWrapper(response as HttpServletResponse)
        chain?.doFilter(requestWrapper, responseWrapper)

        val apiCallLog = ApiCallLog(
            ip = getRequestIpFrom(request),
            requestWrapper = requestWrapper,
            responseWrapper = responseWrapper
        )
        apiCallLogRepository.save(apiCallLog)
    }

    private fun getRequestIpFrom(request: HttpServletRequest): String =
        request.getHeader("X-Forwarded-For")
            ?.let { it.split(",")[0].trim() }
            ?: request.remoteAddr
}
