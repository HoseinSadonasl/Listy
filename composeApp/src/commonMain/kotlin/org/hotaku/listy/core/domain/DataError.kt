package org.hotaku.listy.core.domain

sealed interface DataError: Error {
    object Unknown: DataError
    data class ApiError(val code: Int, val message: String? = null): DataError
    data class LocalError(val error: Throwable): DataError
}