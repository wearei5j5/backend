package org.dev.otte.movie.command.domain

import org.dev.otte.movie.query.dto.MovieRecommendQueryResponse

data class MovieRecommendedEvent(val movieRecommendQueryResponse: MovieRecommendQueryResponse)