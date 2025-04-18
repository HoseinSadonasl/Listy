package org.hotaku.listy.product.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.hotaku.listy.product.data.dao.ProductsDao
import org.hotaku.listy.product.data.model.asEntity
import org.hotaku.listy.product.data.model.asProduct
import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsDao: ProductsDao,
): ProductsRepository {
    override fun getProducts(categoryId: Int?): Flow<List<Product>> =
        productsDao.getProducts(categoryId = categoryId).map { products ->
            products.map { it.asProduct() }
        }

    override suspend fun addProduct(product: Product) = productsDao.upsertProduct(product = product.asEntity())

    override suspend fun deleteProduct(product: Product) = productsDao.deleteProduct(product = product.asEntity())

}