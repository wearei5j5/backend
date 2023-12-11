package org.dev.otte.review.command.application

import org.dev.otte.review.command.domain.Review
import org.dev.otte.review.command.domain.ReviewRepository
import org.dev.otte.review.command.domain.ReviewSatisfaction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReviewService(
    private val reviewRepository: ReviewRepository
) {
    fun register(satisfaction: ReviewSatisfaction) {
        reviewRepository.save(Review(satisfaction))
    }
}