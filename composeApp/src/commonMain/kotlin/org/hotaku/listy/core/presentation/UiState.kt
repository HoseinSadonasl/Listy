package org.hotaku.listy.core.presentation

sealed interface UiState<out D, out E:UiText> {
    object Idle : UiState<Nothing, Nothing>
    object Loading : UiState<Nothing, Nothing>
    data class Success<D>(val data: D): UiState<D, Nothing>
    data class Error<D>(val error: UiText): UiState<Nothing, UiText>
}