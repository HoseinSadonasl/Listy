package org.hotaku.listy.product.domain.usecases

import org.hotaku.listy.product.domain.repository.ProductsRepository

class GetProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(productId: Int) =
        productsRepository.getProductById(productId = productId)
}
