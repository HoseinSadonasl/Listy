package org.hotaku.listy.product.domain.repository

import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.product.domain.model.Product

interface ProductsRepository {
    fun getProducts(categoryId: Int): Flow<List<Product>>
    suspend fun getProductById(productId: Int): Product
    suspend fun addProduct(product: Product)
    suspend fun deleteProduct(product: Product)
}