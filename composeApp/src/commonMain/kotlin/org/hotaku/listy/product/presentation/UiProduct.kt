package org.hotaku.listy.product.presentation

import kotlinx.datetime.Instant
import org.hotaku.listy.product.domain.model.Product

data class UiProduct(
    val id: Int? = null,
    val name: String,
    val description: String,
    val categoryId: Int,
    val importance: Importance,
    val done: Boolean = false,
    val dateCreated: Instant,
)

fun Product.asUiProduct(): UiProduct = UiProduct(
    id = id,
    name = name,
    description = description,
    categoryId = categoryId,
    importance = productImportance.find {
        it.importance.name == importance
    } ?: productImportance.first(),
    done = done,
    dateCreated = Instant.fromEpochSeconds(createdTimestamp),
)

fun UiProduct.asProduct(): Product = Product(
    id = id,
    name = name,
    description = description,
    categoryId = categoryId,
    importance = importance.importance.name,
    done = done,
    createdTimestamp = dateCreated.toEpochMilliseconds() / 1000,
)