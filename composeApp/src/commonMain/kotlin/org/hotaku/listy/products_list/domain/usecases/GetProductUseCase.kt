package org.hotaku.listy.products_list.domain.usecases

import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.repository.ProductsRepository

class GetProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(productId: Int): Product =
        productsRepository.getProduct(productId = productId)
}