package org.dev.otte.common.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class StaticConfig(
    @Column
    var clientBaseUrl: String
) {
    fun changeClientBaseUrl(url: String) {
        clientBaseUrl = url
    }

    @Id
    val id: Long = 0L
}