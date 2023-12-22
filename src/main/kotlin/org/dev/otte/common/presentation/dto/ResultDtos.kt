package org.dev.otte.common.presentation.dto

data class DataResult<T>(
    val data: T
)

data class ErrorResult(
    val message: String?
)