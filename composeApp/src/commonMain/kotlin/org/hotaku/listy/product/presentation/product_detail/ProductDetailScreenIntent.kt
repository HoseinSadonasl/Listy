package org.hotaku.listy.product.presentation.product_detail

sealed interface ProductDetailScreenIntent {
    object OnBackClick : ProductDetailScreenIntent
    object OnSaveClick : ProductDetailScreenIntent
    object OnDoneClick : ProductDetailScreenIntent
    object OnDeleteClick : ProductDetailScreenIntent
    data class OnProductDetailNameChanged(val name: String) : ProductDetailScreenIntent
    data class OnProductDetailDescriptionChanged(val description: String) : ProductDetailScreenIntent
    data class OnProductDetailDoneChanged(val done: Boolean) : ProductDetailScreenIntent
    object OnNewCategory : ProductDetailScreenIntent
    object OnEditCategory : ProductDetailScreenIntent
    object OnSaveCategory : ProductDetailScreenIntent
    data class OnCategoryNameChange(val categoryName: String) : ProductDetailScreenIntent
}