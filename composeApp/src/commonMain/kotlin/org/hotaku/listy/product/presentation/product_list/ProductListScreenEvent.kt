package org.hotaku.listy.product.presentation.product_list

sealed interface ProductListScreenEvent {
    data class NavigateToProductScreen(val productId: Int? = null) : ProductListScreenEvent
}