package org.hotaku.listy.product.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.hotaku.listy.product.data.dao.ProductsDao
import org.hotaku.listy.product.data.model.asEntity
import org.hotaku.listy.product.data.model.asProduct
import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsDao: ProductsDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ProductsRepository {
    override fun getProducts(categoryId: Int?): Flow<List<Product>> =
        productsDao.getProducts(categoryId = categoryId).map { products ->
            products.map { it.asProduct() }
        }

    override suspend fun getProduct(productId: Int): Product =
        withContext(dispatcher){
            productsDao.getProduct(productId = productId).asProduct()
        }

    override suspend fun upsertProduct(product: Product) =
        withContext(dispatcher) {
            productsDao.upsertProduct(product = product.asEntity())
        }

    override suspend fun deleteProduct(product: Product) =
        withContext(dispatcher) {
            productsDao.deleteProduct(product = product.asEntity())
        }

}