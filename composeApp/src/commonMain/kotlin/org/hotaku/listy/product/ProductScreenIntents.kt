package org.hotaku.listy.product

sealed interface ProductScreenIntents {
    object OnBackClick : ProductScreenIntents
    object OnSaveClick : ProductScreenIntents
    object OnDoneClick : ProductScreenIntents
    object OnDeleteClick : ProductScreenIntents
    data class OnProductNameChanged(val name: String) : ProductScreenIntents
    data class OnProductDescriptionChanged(val description: String) : ProductScreenIntents
    data class OnProductDoneChanged(val done: Boolean) : ProductScreenIntents
    object OnNewCategory : ProductScreenIntents
    object OnEditCategory : ProductScreenIntents
    object OnSaveCategory : ProductScreenIntents
    data class OnCategoryNameChange(val categoryName: String) : ProductScreenIntents
}