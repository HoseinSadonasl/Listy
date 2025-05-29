package org.hotaku.listy.product.domain.repository

import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.product.domain.model.Product

interface ProductsRepository {
    fun getProducts(categoryId: Int?, importance: String): Flow<List<Product>>
    suspend fun getProduct(productId: Int): Product
    suspend fun upsertProduct(product: Product)
    suspend fun deleteProduct(product: Product)
}