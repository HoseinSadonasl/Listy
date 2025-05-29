package org.hotaku.listy.product.presentation.product_list

import org.hotaku.listy.product.presentation.ImportanceEnum
import org.hotaku.listy.product.presentation.UiProduct

sealed interface ProductListScreenIntent {
    object OnUpdateList : ProductListScreenIntent
    data class OnImportanceChange(val importance: ImportanceEnum) : ProductListScreenIntent
    data class OnTabChange(val tabIndex: Int) : ProductListScreenIntent
    data class OnDoneClick(val product: UiProduct) : ProductListScreenIntent
    object OnNewProduct : ProductListScreenIntent
    data class OnProductItemClick(val productId: Int) : ProductListScreenIntent
}