package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.dev.otte.common.domain.HttpMessageParser.getRequestBody
import org.dev.otte.common.domain.HttpMessageParser.getResponseBody
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Entity
@Table(name = "api_call_log")
class ApiCallLog(
    @Column
    val ip: String,

    @Column(length = 2000)
    val request: String = "",

    @Column(length = 2000)
    val response: String = ""
) : BaseEntity() {
    constructor(
        ip: String,
        requestWrapper: ContentCachingRequestWrapper,
        responseWrapper: ContentCachingResponseWrapper
    ) : this(
        ip = ip,
        request = getRequestBody(requestWrapper).toString(),
        response = getResponseBody(responseWrapper).toString()
    )
}