package org.hotaku.listy.product.presentation

import kotlinx.datetime.Instant
import org.hotaku.listy.product.domain.Product

data class UiProduct(
    val id: Int,
    val name: String,
    val description: String,
    val emoji: String = "\uD83D\uDECD\uFE0F",
    val categoryId: Int,
    val done: Boolean,
    val dateCreated: Instant,
)

fun Product.asUiProduct(): UiProduct = UiProduct(
    id = id,
    name = name,
    description = description,
    emoji = emoji,
    categoryId = categoryId,
    done = done,
    dateCreated = Instant.fromEpochSeconds(createdTimestamp),
)

fun UiProduct.asProduct(): Product = Product(
    id = id,
    name = name,
    description = description,
    emoji = emoji,
    categoryId = categoryId,
    done = done,
    createdTimestamp = dateCreated.toEpochMilliseconds() / 1000,
)