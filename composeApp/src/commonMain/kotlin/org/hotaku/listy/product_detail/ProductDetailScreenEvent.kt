package org.hotaku.listy.product_detail

sealed interface ProductDetailScreenEvent {
    object NavigateBack : ProductDetailScreenEvent
}