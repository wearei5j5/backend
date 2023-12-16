package org.dev.otte.review.command.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import org.dev.otte.common.domain.BaseEntity

@Entity
@Table(name = "review")
class Review(
    @Column(columnDefinition = "varchar(50)")
    @Enumerated(STRING)
    val satisfaction: ReviewSatisfaction
) : BaseEntity()