package org.hotaku.listy.product

sealed interface ProductScreenIntent {
    object OnBackClick : ProductScreenIntent
    object OnSaveClick : ProductScreenIntent
    object OnDoneClick : ProductScreenIntent
    object OnDeleteClick : ProductScreenIntent
    data class OnProductNameChanged(val name: String) : ProductScreenIntent
    data class OnProductDescriptionChanged(val description: String) : ProductScreenIntent
    object OnNewCategory : ProductScreenIntent
    object OnEditCategory : ProductScreenIntent
    object OnSaveCategory : ProductScreenIntent
    data class OnCategoryNameChange(val categoryName: String) : ProductScreenIntent
}