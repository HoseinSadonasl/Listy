package org.hotaku.listy.product.di

import org.hotaku.listy.product.data.repository.ProductsRepositoryImpl
import org.hotaku.listy.product.domain.usecases.AddProductUseCase
import org.hotaku.listy.product.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.product.domain.usecases.GetProductUseCase
import org.hotaku.listy.product.domain.usecases.GetProductsUseCase
import org.hotaku.listy.product.domain.repository.ProductsRepository
import org.hotaku.listy.product.presentation.ProductsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val productsModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl(productsDao = get()) }

    singleOf(::GetProductsUseCase)
    singleOf(::AddProductUseCase)
    singleOf(::GetProductUseCase)
    singleOf(::DeleteProductUseCase)

    singleOf(::ProductsViewModel)
}