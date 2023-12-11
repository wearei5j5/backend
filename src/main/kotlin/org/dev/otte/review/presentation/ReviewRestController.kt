package org.dev.otte.review.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.review.command.application.ReviewService
import org.dev.otte.review.presentation.dto.ReviewRegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Review")
@RestController
@RequestMapping("/api/v1/review")
class ReviewRestController(
    private val reviewService: ReviewService
) {
    @PostMapping
    @Operation(summary = "Register I5J5 review")
    fun register(@RequestBody request: ReviewRegisterRequest): ResponseEntity<Any> {
        reviewService.register(request.satisfaction)
        return ResponseEntity.ok().build()
    }
}




