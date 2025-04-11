package org.hotaku.listy.product.domain

class AddProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(product: Product) = productsRepository.addProduct(product = product)
}