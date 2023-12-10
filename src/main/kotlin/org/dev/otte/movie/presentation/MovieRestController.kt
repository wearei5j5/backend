package org.dev.otte.movie.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.dev.otte.common.presentation.DataResult
import org.dev.otte.movie.presentation.dto.MovieRecommendRequest
import org.dev.otte.movie.query.MovieRecommendQueryService
import org.dev.otte.movie.query.dto.MovieRecommendQueryResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Movie")
@RestController
@RequestMapping("/api/v1/movie")
class MovieRestController(
    private val movieRecommendQueryService: MovieRecommendQueryService
) {
    @GetMapping("/recommended")
    @Operation(summary = "Find Recommended Movie")
    fun recommend(request: MovieRecommendRequest): ResponseEntity<DataResult<List<MovieRecommendQueryResponse>>> {
        val response = movieRecommendQueryService.recommend(
            ottList = request.ottList, feeling = request.feeling, situation = request.situation
        )
        return ResponseEntity.ok(DataResult(response))
    }
}