package org.hotaku.listy.product.presentation.product_detail

import org.jetbrains.compose.resources.StringResource

sealed interface ProductDetailScreenEvent {
    object NavigateBack : ProductDetailScreenEvent
    data class ShowSnackbar(val messageRes: StringResource) : ProductDetailScreenEvent
}