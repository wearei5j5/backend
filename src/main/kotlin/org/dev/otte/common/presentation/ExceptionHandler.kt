package org.dev.otte.common.presentation

import io.jsonwebtoken.ClaimJwtException
import io.jsonwebtoken.security.SignatureException
import org.dev.otte.common.presentation.dto.ErrorResult
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error("message", ex)
        val message = ex.message.orEmpty()
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResult(message))
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResult(ex.messages()))
    }

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorResult> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResult(ex.message))
    }

    @ExceptionHandler(ClaimJwtException::class, SignatureException::class)
    fun handleLoginFailedException(ex: RuntimeException): ResponseEntity<ErrorResult> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResult(ex.message))
    }

    private fun MethodArgumentNotValidException.messages() =
        bindingResult.fieldErrors.joinToString(", ") { "${it.field}: ${it.defaultMessage.orEmpty()}" }
}