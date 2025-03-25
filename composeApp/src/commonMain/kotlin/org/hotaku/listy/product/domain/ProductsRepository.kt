package org.hotaku.listy.product.domain

interface ProductsRepository {
    fun getProducts(categoryId: Int): Result<List<Product>>
    fun getProductById(productId: Int)
    fun addProduct(product: Product)
    fun deleteProduct(product: Product)
}