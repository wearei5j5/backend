package org.dev.otte.folder.command.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.dev.otte.common.domain.BaseEntity

@Entity
@Table(name = "folder")
class Folder(
    @Column(name = "userId")
    val userId: Long,

    @Column(name = "name")
    var name: String
) : BaseEntity() {
    fun update(name: String) {
        this.name = name
    }
}