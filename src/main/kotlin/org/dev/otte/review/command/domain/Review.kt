package org.dev.otte.review.command.domain

import jakarta.persistence.*

@Entity
class Review(
    @Column
    val satisfaction: ReviewSatisfaction
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}