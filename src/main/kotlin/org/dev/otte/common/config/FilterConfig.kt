package org.dev.otte.common.config

import org.dev.otte.common.filter.ApiCallLogFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig(
    private val apiCallLogFilter: ApiCallLogFilter
) {
    @Bean
    fun loggingFilter(): FilterRegistrationBean<ApiCallLogFilter> {
        val registrationBean = FilterRegistrationBean<ApiCallLogFilter>()
        registrationBean.filter = apiCallLogFilter
        registrationBean.addUrlPatterns("/api/v1/movie/recommended")
        registrationBean.order = 1
        return registrationBean
    }
}