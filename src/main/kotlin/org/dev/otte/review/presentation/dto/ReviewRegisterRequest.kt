package org.dev.otte.review.presentation.dto

import org.dev.otte.review.command.domain.ReviewSatisfaction

data class ReviewRegisterRequest(
    val satisfaction: ReviewSatisfaction
)