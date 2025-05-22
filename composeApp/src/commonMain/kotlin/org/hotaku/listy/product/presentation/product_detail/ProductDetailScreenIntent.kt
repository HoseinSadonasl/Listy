package org.hotaku.listy.product.presentation.product_detail

sealed interface ProductDetailScreenIntent {
    object OnBackClick : ProductDetailScreenIntent
    object OnSaveClick : ProductDetailScreenIntent
    object OnDoneClick : ProductDetailScreenIntent
    object OnDeleteClick : ProductDetailScreenIntent
    object OnDeleteProduct : ProductDetailScreenIntent
    object OnHideDeleteDialog : ProductDetailScreenIntent
    data class OnProductDetailNameChanged(val name: String) : ProductDetailScreenIntent
    data class OnProductDetailDescriptionChanged(val description: String) : ProductDetailScreenIntent
    data class OnProductDetailDoneChanged(val done: Boolean) : ProductDetailScreenIntent
    object OnNewCategory : ProductDetailScreenIntent
    object OnSaveCategory : ProductDetailScreenIntent
    data class OnEditCategory(val id: Int) : ProductDetailScreenIntent
    data class OnSetProductCategory(val id: Int) : ProductDetailScreenIntent
    data class OnCategoryNameChange(val categoryName: String) : ProductDetailScreenIntent
}