package org.hotaku.listy.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductScreenRoute(
    val productId: Int? = null,
)
