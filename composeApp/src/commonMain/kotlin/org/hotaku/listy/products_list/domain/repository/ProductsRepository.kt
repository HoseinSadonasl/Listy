package org.hotaku.listy.products_list.domain.repository

import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.products_list.domain.model.Product

interface ProductsRepository {
    fun getProducts(categoryId: Int?): Flow<List<Product>>
    suspend fun addProduct(product: Product)
    suspend fun deleteProduct(product: Product)
}