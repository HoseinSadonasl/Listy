package org.hotaku.listy.product.domain

import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val productsRepository: ProductsRepository,
) {
    operator fun invoke(categoryId: Int): Flow<List<Product>> =
        productsRepository.getProducts(categoryId = categoryId)
}