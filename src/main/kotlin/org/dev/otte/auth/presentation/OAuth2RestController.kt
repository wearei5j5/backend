package org.dev.otte.auth.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import org.dev.otte.auth.command.application.OAuth2Service
import org.dev.otte.auth.command.application.dto.TokenResponse
import org.dev.otte.auth.presentation.dto.OAuth2LoginRequest
import org.dev.otte.common.application.StaticConfigService
import org.dev.otte.common.presentation.dto.DataResult
import org.dev.otte.user.command.domain.SocialProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "OAuth2")
@RestController
@RequestMapping("/oauth2")
class OAuth2RestController(
    private val oAuth2Service: OAuth2Service,
    private val staticConfigService: StaticConfigService
) {
    @GetMapping("/code/{providerString}")
    @Operation(
        summary = "Redirect to Social Login Page",
        description = "소셜로그인 성공 후 clientBaseUrl://code={authorization_code}&state={customString}&prover={provider} 형태로 리다이렉트"
    )
    fun redirectGetAuthCodePage(
        @PathVariable providerString: String,
        state: String?,
        response: HttpServletResponse
    ) {
        val targetUrl = oAuth2Service.getRedirectUrl(SocialProvider.of(providerString), state)
        response.sendRedirect(targetUrl)
    }

    @GetMapping("/callback/{providerString}")
    fun redirectCodeAndStateToClient(
        @PathVariable providerString: String,
        code: String,
        state: String?,
        response: HttpServletResponse
    ) {
        response.sendRedirect(getTargetUrl(code, state, providerString))
    }

    @PostMapping("/login")
    @Operation(
        summary = "Login via social_authorization_code & provider",
        description = "소셜 로그인 성공 후 리다이렉트 된 url에 포함된 authorization_code와 provider를 통해 엑세스 토큰 취득"
    )
    fun login(@RequestBody request: OAuth2LoginRequest): ResponseEntity<DataResult<TokenResponse>> {
        return ResponseEntity.ok(DataResult(oAuth2Service.login(request.toCommand())))
    }

    private fun getTargetUrl(code: String, state: String?, providerString: String) =
        "${staticConfigService.get().clientBaseUrl}?code=$code&state=${state ?: ""}&provider=$providerString"
}