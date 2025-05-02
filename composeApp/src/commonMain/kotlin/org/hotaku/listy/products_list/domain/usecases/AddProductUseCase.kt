package org.hotaku.listy.products_list.domain.usecases

import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.repository.ProductsRepository

class AddProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(product: Product) = productsRepository.addProduct(product = product)
}