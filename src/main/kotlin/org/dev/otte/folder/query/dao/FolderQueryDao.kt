package org.dev.otte.folder.query.dao

import org.dev.otte.common.annotation.Dao
import org.dev.otte.folder.query.dao.support.FolderQueryDaoSupport
import org.dev.otte.folder.query.dto.FolderQueryResponse
import org.dev.otte.movie.query.dao.support.MovieQueryDaoSupport

@Dao
class FolderQueryDao(
    private val folderQueryDaoSupport: FolderQueryDaoSupport,
    private val movieQueryDaoSupport: MovieQueryDaoSupport
) {
    fun findAll(userId: Long): List<FolderQueryResponse> {
        val folders = folderQueryDaoSupport.findAllByUserId(userId)
        val movieGroup = movieQueryDaoSupport
            .findAllByFolderIdIn(folders.map { it.id })
            .groupBy { it.folderId }
        return folders.map {
            FolderQueryResponse(it, movieGroup[(it.id)] ?: emptyList())
        }
    }
}


