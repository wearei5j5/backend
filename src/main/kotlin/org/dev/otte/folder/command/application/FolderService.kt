package org.dev.otte.folder.command.application

import org.dev.otte.folder.command.domain.Folder
import org.dev.otte.folder.command.domain.FolderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FolderService(
    private val folderRepository: FolderRepository
) {
    fun register(userId: Long, name: String) {
        folderRepository.save(Folder(userId, name))
    }

    fun update(userId: Long, folderId: Long, name: String) {
        val folder = folderRepository.findByIdOrNull(folderId) ?: throw IllegalArgumentException()
        check(userId == folder.userId)
        folder.update(name)
    }
}