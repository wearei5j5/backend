package org.dev.otte.movie.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.common.presentation.dto.DataResult
import org.dev.otte.common.security.AuthenticationFacade
import org.dev.otte.movie.command.application.MovieService
import org.dev.otte.movie.presentation.dto.MovieRecommendRequest
import org.dev.otte.movie.presentation.dto.MovieSaveRequest
import org.dev.otte.movie.query.MovieQueryService
import org.dev.otte.movie.query.MovieRecommendQueryService
import org.dev.otte.movie.query.dto.MovieQueryResponse
import org.dev.otte.movie.query.dto.MovieRecommendQueryResponse
import org.dev.otte.user.command.domain.User
import org.springdoc.core.annotations.ParameterObject
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "Movie")
@RestController
@RequestMapping("/api/v1/movie")
class MovieRestController(
    private val movieRecommendQueryService: MovieRecommendQueryService,
    private val movieService: MovieService,
    private val movieQueryService: MovieQueryService,
    private val facade: AuthenticationFacade
) {
    @GetMapping("/recommended")
    @Operation(summary = "Find Recommended Movie")
    fun recommend(@ParameterObject request: MovieRecommendRequest): ResponseEntity<DataResult<List<MovieRecommendQueryResponse>>> {
        val response = movieRecommendQueryService.recommend(request.toCondition(facade.getUserIdOrNull()))
        return ResponseEntity.ok(DataResult(response))
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    @Operation(summary = "Save Recommended Movie")
    fun save(
        @AuthenticationPrincipal user: User,
        @RequestBody request: MovieSaveRequest
    ): ResponseEntity<Any> {
        movieService.save(request.toCommand(user.id))
        return ResponseEntity.ok().build()
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    @Operation(summary = "Find All Saved User's Movie")
    fun findAll(
        @AuthenticationPrincipal user: User
    ): ResponseEntity<DataResult<List<MovieQueryResponse>>> {
        return ResponseEntity.ok(DataResult(movieQueryService.findAll(user.id)))
    }
}

