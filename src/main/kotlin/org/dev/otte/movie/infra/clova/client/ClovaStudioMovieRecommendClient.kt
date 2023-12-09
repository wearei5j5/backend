package org.dev.otte.movie.infra.clova.client

import org.dev.otte.movie.infra.clova.config.ClovaStudioConfig
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendRequest
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendResult
import org.springframework.stereotype.Component

@Component
class ClovaStudioMovieRecommendClient(
    private val clovaApiClient: ClovaApiClient,
    private val clovaStudioConfig: ClovaStudioConfig
) {
    fun fetchMovieRecommend(request: ClovaStudioMovieRecommendRequest): ClovaStudioMovieRecommendResult {
        return clovaApiClient.fetchMovieRecommend(
            clovaStudioApiKey = clovaStudioConfig.clovaStudioApiKey,
            apiGatewayApiKey = clovaStudioConfig.apiGatewayApiKey,
            clovaStudioRequestId = clovaStudioConfig.clovaStudioRequestId,
            request = request
        )
    }
}
