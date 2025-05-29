package org.hotaku.listy.product.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.domain.repository.ProductsRepository

class GetProductsUseCase(
    private val productsRepository: ProductsRepository,
) {
    operator fun invoke(categoryId: Int?, importance: String): Flow<List<Product>> =
        productsRepository.getProducts(categoryId = categoryId, importance = importance)
}