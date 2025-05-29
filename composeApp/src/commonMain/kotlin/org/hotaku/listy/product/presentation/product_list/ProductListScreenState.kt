package org.hotaku.listy.product.presentation.product_list

import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.product.presentation.ImportanceEnum
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.productImportance

data class ProductListScreenState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val categories: List<UiCategory> = emptyList(),
    val products: List<UiProduct> = emptyList(),
    val categoryId: Int? = null,
    val product: UiProduct? = null,
    val selectedCategory: Int = 0,
    val selectedImportance: ImportanceEnum = productImportance.first().importance,
    val isBottomSheetOpen: Boolean = false,
)
