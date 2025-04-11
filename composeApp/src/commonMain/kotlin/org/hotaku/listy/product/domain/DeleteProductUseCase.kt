package org.hotaku.listy.product.domain

class DeleteProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(product: Product) = productsRepository.deleteProduct(product = product)
}