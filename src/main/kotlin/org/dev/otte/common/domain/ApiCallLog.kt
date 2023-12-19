package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ApiCallLog(
    @Column
    val ip: String
) : BaseEntity()