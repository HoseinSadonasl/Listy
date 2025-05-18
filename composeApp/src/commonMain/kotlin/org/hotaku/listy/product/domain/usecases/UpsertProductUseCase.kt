package org.hotaku.listy.product.domain.usecases

import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.domain.repository.ProductsRepository

class UpsertProductUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke(product: Product) = productsRepository.upsertProduct(product = product)
}