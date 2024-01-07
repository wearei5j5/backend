package org.dev.otte.folder.presentation.dto

import jakarta.validation.constraints.NotBlank

data class FolderRegisterRequest(
    @field:NotBlank
    val name: String
)