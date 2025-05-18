package org.hotaku.listy.product.presentation.product_list

sealed interface ProductListScreenIntent {
    data class OnTabClick(val tabIndex: Int) : ProductListScreenIntent
    data class OnDoneClick(val productId: Int) : ProductListScreenIntent
    object OnNewProduct : ProductListScreenIntent
    data class OnProductItemClick(val productId: Int) : ProductListScreenIntent
}