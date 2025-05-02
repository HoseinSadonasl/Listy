package org.hotaku.listy.products_list.domain.usecases

import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.repository.ProductsRepository

class DeleteProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(product: Product) = productsRepository.deleteProduct(product = product)
}