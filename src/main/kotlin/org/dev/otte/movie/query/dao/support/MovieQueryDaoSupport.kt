package org.dev.otte.movie.query.dao.support

import org.dev.otte.movie.command.domain.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieQueryDaoSupport : JpaRepository<Movie, Long> {
    fun findAllByUserId(userId: Long): List<Movie>
    fun findAllByFolderIdIn(folderIds: Collection<Long>): List<Movie>
}