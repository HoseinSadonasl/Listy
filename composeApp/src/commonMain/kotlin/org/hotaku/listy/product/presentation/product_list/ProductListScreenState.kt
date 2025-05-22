package org.hotaku.listy.product.presentation.product_list

import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.product.presentation.UiProduct

data class ProductListScreenState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val categories: List<UiCategory> = emptyList(),
    val products: List<UiProduct> = emptyList(),
    val categoryId: Int? = null,
    val product: UiProduct? = null,
    val selectedCategory: Int = 0,
    val isBottomSheetOpen: Boolean = false,
)
