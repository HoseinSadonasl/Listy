package org.hotaku.listy.product.presentation

sealed interface ProductsScreenIntents {
    data class OnTabClick(val tabIndex: Int) : ProductsScreenIntents
    data class OnDoneClick(val product: UiProduct) : ProductsScreenIntents
    data class OnEmojiChange(val query: String) : ProductsScreenIntents
    data class OnProductTitleChange(val query: String) : ProductsScreenIntents
    data class OnProductDescriptionChange(val query: String) : ProductsScreenIntents
    object OnSaveProduct : ProductsScreenIntents
    object OnDeleteProduct : ProductsScreenIntents
    data class OnOpenEditItemSheet(val product: UiProduct? = null) : ProductsScreenIntents
    object OnBottomSheetDismiss : ProductsScreenIntents
}