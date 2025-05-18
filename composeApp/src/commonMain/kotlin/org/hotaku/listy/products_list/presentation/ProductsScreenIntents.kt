package org.hotaku.listy.products_list.presentation

sealed interface ProductsScreenIntents {
    data class OnTabClick(val tabIndex: Int) : ProductsScreenIntents
    data class OnDoneClick(val productId: Int) : ProductsScreenIntents
    object OnNewProduct : ProductsScreenIntents
    data class OnProductItemClick(val productId: Int) : ProductsScreenIntents
}