package org.hotaku.listy.products_list.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.repository.ProductsRepository

class GetProductsUseCase(
    private val productsRepository: ProductsRepository,
) {
    operator fun invoke(categoryId: Int): Flow<List<Product>> =
        productsRepository.getProducts(categoryId = categoryId)
}