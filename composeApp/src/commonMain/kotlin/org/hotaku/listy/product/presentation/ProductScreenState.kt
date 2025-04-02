package org.hotaku.listy.product.presentation

import org.hotaku.listy.category.presentation.UiCategory

data class ProductScreenState(
    val categories: List<UiCategory> = emptyList(),
    val products: List<UiProduct> = emptyList(),
    val selectedTabIndex: Int = 0,
)
