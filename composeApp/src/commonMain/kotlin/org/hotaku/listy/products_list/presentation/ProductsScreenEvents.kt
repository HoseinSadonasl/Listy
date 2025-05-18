package org.hotaku.listy.products_list.presentation

sealed interface ProductsScreenEvents {
    data class NavigateToProductScreen(val productId: Int? = null) : ProductsScreenEvents
}