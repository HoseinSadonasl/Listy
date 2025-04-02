package org.hotaku.listy.product.presentation

sealed interface ProductsScreenIntents {
    object OnAddItemClick : ProductsScreenIntents
    data class OnTabClick(val tabIndex: Int) : ProductsScreenIntents
    data class OnDoneClick(val product: UiProduct) : ProductsScreenIntents
}