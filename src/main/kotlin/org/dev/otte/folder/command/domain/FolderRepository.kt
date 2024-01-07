package org.dev.otte.folder.command.domain

import org.springframework.data.jpa.repository.JpaRepository

interface FolderRepository : JpaRepository<Folder, Long>