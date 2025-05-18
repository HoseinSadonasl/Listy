package org.hotaku.listy.product.domain.model

data class Product(
    val id: Int?,
    val name: String,
    val description: String,
    val categoryId: Int,
    val done: Boolean,
    val createdTimestamp: Long,
)