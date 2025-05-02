package org.hotaku.listy.products_list.di

import org.hotaku.listy.products_list.data.repository.ProductsRepositoryImpl
import org.hotaku.listy.products_list.domain.usecases.AddProductUseCase
import org.hotaku.listy.products_list.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.products_list.domain.usecases.GetProductsUseCase
import org.hotaku.listy.products_list.domain.repository.ProductsRepository
import org.hotaku.listy.products_list.presentation.ProductsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val productsModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl(productsDao = get()) }

    singleOf(::GetProductsUseCase)
    singleOf(::AddProductUseCase)
    singleOf(::DeleteProductUseCase)

    singleOf(::ProductsViewModel)
}