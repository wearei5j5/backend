package org.dev.otte.folder.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.dev.otte.common.presentation.dto.DataResult
import org.dev.otte.common.security.AuthenticationFacade
import org.dev.otte.folder.command.application.FolderService
import org.dev.otte.folder.presentation.dto.FolderRegisterRequest
import org.dev.otte.folder.presentation.dto.FolderUpdateRequest
import org.dev.otte.folder.query.FolderQueryService
import org.dev.otte.folder.query.dto.FolderQueryResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Folder")
@RestController
@RequestMapping("/api/v1/folders")
@PreAuthorize("hasRole('ROLE_BASIC')")
class FolderRestController(
    private val folderQueryService: FolderQueryService,
    private val folderService: FolderService,
    private val facade: AuthenticationFacade
) {
    @GetMapping
    @Operation(summary = "Find All User's Folder")
    fun findAll(): ResponseEntity<DataResult<List<FolderQueryResponse>>> {
        return ResponseEntity.ok(DataResult(folderQueryService.findAll(facade.getUserId())))
    }

    @PostMapping
    @Operation(summary = "Register folder")
    fun register(@RequestBody @Valid request: FolderRegisterRequest): ResponseEntity<Any> {
        folderService.register(facade.getUserId(), request.name)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{folderId}")
    @Operation(summary = "Update folder name")
    fun update(
        @PathVariable folderId: Long,
        @RequestBody @Valid request: FolderUpdateRequest
    ): ResponseEntity<Any> {
        folderService.update(facade.getUserId(), folderId, request.name)
        return ResponseEntity.ok().build()
    }
}