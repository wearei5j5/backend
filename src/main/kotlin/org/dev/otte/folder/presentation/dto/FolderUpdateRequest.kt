package org.dev.otte.folder.presentation.dto

import jakarta.validation.constraints.NotBlank

data class FolderUpdateRequest(
    @field:NotBlank
    val name: String
)