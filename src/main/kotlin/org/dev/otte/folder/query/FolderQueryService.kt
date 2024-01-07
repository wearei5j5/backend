package org.dev.otte.folder.query

import org.dev.otte.folder.query.dao.FolderQueryDao
import org.dev.otte.folder.query.dto.FolderQueryResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FolderQueryService(
    private val folderQueryDao: FolderQueryDao
) {
    fun findAll(userId: Long): List<FolderQueryResponse> {
        return folderQueryDao.findAll(userId)
    }
}