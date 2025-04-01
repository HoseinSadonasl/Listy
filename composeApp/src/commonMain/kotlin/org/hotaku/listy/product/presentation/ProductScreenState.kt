package org.hotaku.listy.product.presentation

import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.core.presentation.UiState
import org.hotaku.listy.core.presentation.UiText

data class ProductScreenState(
    val categories: UiState<List<UiCategory>, UiText> = UiState.Idle,
    val products: UiState<List<UiProduct>, UiText> = UiState.Idle
)
