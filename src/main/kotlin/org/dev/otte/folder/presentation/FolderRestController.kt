package org.dev.otte.folder.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.common.presentation.dto.DataResult
import org.dev.otte.common.security.AuthenticationFacade
import org.dev.otte.folder.query.FolderQueryService
import org.dev.otte.folder.query.dto.FolderQueryResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Folder")
@RestController
@RequestMapping("/api/v1/folders")
class FolderRestController(
    private val folderQueryService: FolderQueryService,
    private val facade: AuthenticationFacade
) {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    @Operation(summary = "Find All User's Folder")
    fun findAll(): ResponseEntity<DataResult<List<FolderQueryResponse>>> {
        return ResponseEntity.ok(DataResult(folderQueryService.findAll(facade.getUserId())))
    }
}