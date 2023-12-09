package org.dev.otte.movie.infra.clova.client

import org.dev.otte.movie.infra.clova.config.ClovaStudioProperties
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendRequest
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendResult
import org.springframework.stereotype.Component

@Component
class ClovaStudioMovieRecommendClient(
    private val clovaApiClient: ClovaApiClient,
    private val clovaStudioProperties: ClovaStudioProperties
) {
    fun fetchMovieRecommend(request: ClovaStudioMovieRecommendRequest): ClovaStudioMovieRecommendResult {
        return clovaApiClient.fetchMovieRecommend(
            clovaStudioApiKey = clovaStudioProperties.clovaStudioApiKey,
            apiGatewayApiKey = clovaStudioProperties.apiGatewayApiKey,
            clovaStudioRequestId = clovaStudioProperties.clovaStudioRequestId,
            request = request
        )
    }
}
