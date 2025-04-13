package org.hotaku.listy.product.presentation

import org.hotaku.listy.category.presentation.UiCategory

data class ProductScreenState(
    val categories: List<UiCategory> = emptyList(),
    val products: List<UiProduct> = emptyList(),
    val product: UiProduct? = null,
    val selectedTabIndex: Int = 0,
    val isBottomSheetOpen: Boolean = false,
)
