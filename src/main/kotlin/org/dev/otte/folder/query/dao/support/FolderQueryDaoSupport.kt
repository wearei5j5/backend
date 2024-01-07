package org.dev.otte.folder.query.dao.support

import org.dev.otte.folder.command.domain.Folder
import org.springframework.data.jpa.repository.JpaRepository

interface FolderQueryDaoSupport : JpaRepository<Folder, Long> {
    fun findAllByUserId(userId: Long): List<Folder>
}