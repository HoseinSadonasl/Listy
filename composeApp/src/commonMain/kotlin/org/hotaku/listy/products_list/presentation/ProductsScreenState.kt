package org.hotaku.listy.products_list.presentation

import org.hotaku.listy.category.presentation.UiCategory

data class ProductsScreenState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val categories: List<UiCategory> = emptyList(),
    val products: List<UiProduct> = emptyList(),
    val categoryId: Int? = null,
    val product: UiProduct? = null,
    val selectedTabIndex: Int = 0,
    val isBottomSheetOpen: Boolean = false,
)
