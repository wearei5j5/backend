package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "static_config")
class StaticConfig(
    @Column
    var clientBaseUrl: String
) : BaseEntity() {
    fun changeClientBaseUrl(url: String) {
        clientBaseUrl = url
    }
}