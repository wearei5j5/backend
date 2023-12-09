package org.dev.otte.movie.infra.clova.client

import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendRequest
import org.dev.otte.movie.infra.clova.dto.ClovaStudioMovieRecommendResult
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.PostExchange

private const val X_NCP_CLOVASTUDIO_API_KEY = "X-NCP-CLOVASTUDIO-API-KEY"
private const val X_NCP_APIGW_API_KEY = "X-NCP-APIGW-API-KEY"
private const val X_NCP_CLOVASTUDIO_REQUEST_ID = "X-NCP-CLOVASTUDIO-REQUEST-ID"

interface ClovaApiClient {
    @PostExchange(
        "https://clovastudio.apigw.ntruss.com/testapp/v1/completions/LK-D2",
        contentType = APPLICATION_JSON_VALUE
    )
    fun fetchMovieRecommend(
        @RequestHeader(name = X_NCP_CLOVASTUDIO_API_KEY) clovaStudioApiKey: String,
        @RequestHeader(name = X_NCP_APIGW_API_KEY) apiGatewayApiKey: String,
        @RequestHeader(name = X_NCP_CLOVASTUDIO_REQUEST_ID) clovaStudioRequestId: String,
        @RequestBody request: ClovaStudioMovieRecommendRequest
    ): ClovaStudioMovieRecommendResult
}
