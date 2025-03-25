package org.hotaku.listy.product.domain

data class Product(
    val id: Int,
    val name: String,
    val emoji: String,
    val categoryId: Int,
    val done: Boolean,
    val date: String,
)
