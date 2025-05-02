package org.hotaku.listy.products_list.presentation

sealed interface ProductsScreenIntents {
    data class OnTabClick(val tabIndex: Int) : ProductsScreenIntents
    data class OnDoneClick(val product: UiProduct) : ProductsScreenIntents
    data class OnEmojiChange(val query: String) : ProductsScreenIntents
    data class OnProductTitleChange(val query: String) : ProductsScreenIntents
    data class OnProductDescriptionChange(val query: String) : ProductsScreenIntents
    object OnSaveProduct : ProductsScreenIntents
    object OnDeleteProduct : ProductsScreenIntents
    object OnNewProduct : ProductsScreenIntents
    data class OnOpenEditItemSheet(val product: UiProduct? = null) : ProductsScreenIntents
    object OnBottomSheetDismiss : ProductsScreenIntents
    object OnNewCategory : ProductsScreenIntents
    data class OnNewCategoryNameChange(val query: String) : ProductsScreenIntents
}