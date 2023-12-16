package org.dev.otte.movie.infra.tmdb.client

import org.dev.otte.movie.infra.tmdb.dto.TmdbSearchMovieResult
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange

interface TmdbApiClient {
    @GetExchange("https://api.themoviedb.org/3/search/movie?include_adult=false&language=ko&page=1")
    fun searchMovie(
        @RequestHeader(name = HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestParam(name = "query") movieName: String
    ): TmdbSearchMovieResult
}
