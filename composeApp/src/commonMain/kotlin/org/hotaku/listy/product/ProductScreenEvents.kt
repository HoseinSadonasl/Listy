package org.hotaku.listy.product

sealed interface ProductScreenEvents {
    object NavigateBack : ProductScreenEvents
    object UnknownError : ProductScreenEvents
}