package org.hotaku.listy.product.domain

import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(categoryId: Int): Flow<List<Product>>
    suspend fun getProductById(productId: Int): Product
    suspend fun addProduct(product: Product)
    suspend fun deleteProduct(product: Product)
}