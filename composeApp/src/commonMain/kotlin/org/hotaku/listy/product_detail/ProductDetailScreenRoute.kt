package org.hotaku.listy.product_detail

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailScreenRoute(
    val productId: Int? = null,
)
