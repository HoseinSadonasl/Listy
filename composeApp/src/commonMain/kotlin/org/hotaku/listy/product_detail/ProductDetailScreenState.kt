package org.hotaku.listy.product_detail

import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.products_list.presentation.UiProduct

data class ProductDetailScreenState(
    val isLoading: Boolean = false,
    val product: UiProduct? = null,
    val categories: List<UiCategory> = emptyList(),
    val category: UiCategory? = null,
)
