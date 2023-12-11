package org.dev.otte.common.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.common.application.StaticConfigService
import org.dev.otte.common.presentation.dto.ClientBaseUrlRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Config")
@RestController
@RequestMapping("/config")
class StaticConfigController(
    private val staticConfigService: StaticConfigService
) {
    @PostMapping("/clientBaseUrl")
    @Operation(summary = "소셜로그인 성공 후 인가코드가 리다이렉트 될 base url 변경")
    fun changeClientBaseUrl(@RequestBody request: ClientBaseUrlRequest): ResponseEntity<Any> {
        staticConfigService.changeClientBaseUrl(request.url)
        return ResponseEntity.ok().build()
    }
}