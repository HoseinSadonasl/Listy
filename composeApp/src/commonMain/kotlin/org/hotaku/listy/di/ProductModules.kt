package org.hotaku.listy.di

import org.hotaku.listy.product.ProductViewModel
import org.hotaku.listy.products_list.data.repository.ProductsRepositoryImpl
import org.hotaku.listy.products_list.domain.usecases.UpsertProductUseCase
import org.hotaku.listy.products_list.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.products_list.domain.usecases.GetProductsUseCase
import org.hotaku.listy.products_list.domain.repository.ProductsRepository
import org.hotaku.listy.products_list.domain.usecases.GetProductUseCase
import org.hotaku.listy.products_list.presentation.ProductsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productsModule = module {
    single<ProductsRepository> {
        ProductsRepositoryImpl(
            productsDao = get(),
        )
    }

    singleOf(::GetProductsUseCase)
    singleOf(::GetProductUseCase)
    singleOf(::UpsertProductUseCase)
    singleOf(::DeleteProductUseCase)

    viewModelOf(::ProductsViewModel)
    viewModelOf(::ProductViewModel)
}