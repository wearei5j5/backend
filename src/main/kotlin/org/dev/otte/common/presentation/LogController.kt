package org.dev.otte.common.presentation

import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.common.domain.ApiCallLog
import org.dev.otte.common.domain.ApiCallLogRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Log")
@RestController
@RequestMapping("/api/logs")
class LogController(
    private val apiCallLogRepository: ApiCallLogRepository
) {
    @GetMapping
    fun findAllLogs(): MutableList<ApiCallLog> {
        return apiCallLogRepository.findAll()
    }
}