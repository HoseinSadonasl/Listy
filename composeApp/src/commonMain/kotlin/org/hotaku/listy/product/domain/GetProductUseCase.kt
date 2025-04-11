package org.hotaku.listy.product.domain

class GetProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(productId: Int) =
        productsRepository.getProductById(productId = productId)
}
