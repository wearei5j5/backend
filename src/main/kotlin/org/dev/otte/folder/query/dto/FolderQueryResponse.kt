package org.dev.otte.folder.query.dto

import org.dev.otte.folder.command.domain.Folder
import org.dev.otte.movie.command.domain.Movie
import org.dev.otte.movie.query.dto.MovieQueryResponse

data class FolderQueryResponse(
    val folderId: Long,
    val folderName: String,
    val movies: List<MovieQueryResponse>
) {
    constructor(folder: Folder, movies: List<Movie>) : this(
        folder.id,
        folder.name,
        movies.map { MovieQueryResponse(it) }
    )
}