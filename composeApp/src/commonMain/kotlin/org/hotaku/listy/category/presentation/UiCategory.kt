package org.hotaku.listy.category.presentation

import org.hotaku.listy.category.domain.Category

data class UiCategory(
    val id: Int,
    val name: String,
)

fun Category.asUiCategory(): UiCategory = UiCategory(
    id = id, name = name
)

fun UiCategory.asCategory(): Category = Category(
    id = id, name = name
)