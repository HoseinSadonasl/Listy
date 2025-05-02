package org.hotaku.listy.products_list.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.hotaku.listy.products_list.data.dao.ProductsDao
import org.hotaku.listy.products_list.data.model.asEntity
import org.hotaku.listy.products_list.data.model.asProduct
import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.repository.ProductsRepository

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