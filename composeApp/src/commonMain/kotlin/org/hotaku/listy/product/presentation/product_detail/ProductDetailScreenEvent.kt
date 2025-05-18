package org.hotaku.listy.product.presentation.product_detail

sealed interface ProductDetailScreenEvent {
    object NavigateBack : ProductDetailScreenEvent
}