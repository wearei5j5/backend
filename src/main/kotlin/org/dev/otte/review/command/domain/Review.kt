package org.dev.otte.review.command.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.dev.otte.common.domain.BaseEntity

@Entity
@Table(name = "review")
class Review(
    @Column
    val satisfaction: ReviewSatisfaction
) : BaseEntity()