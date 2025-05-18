package org.hotaku.listy.di

import org.hotaku.listy.product.presentation.product_detail.ProductDetailViewModel
import org.hotaku.listy.product.data.repository.ProductsRepositoryImpl
import org.hotaku.listy.product.domain.usecases.UpsertProductUseCase
import org.hotaku.listy.product.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.product.domain.usecases.GetProductsUseCase
import org.hotaku.listy.product.domain.repository.ProductsRepository
import org.hotaku.listy.product.domain.usecases.GetProductUseCase
import org.hotaku.listy.product.presentation.product_list.ProductListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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

    viewModelOf(::ProductListViewModel)
    viewModelOf(::ProductDetailViewModel)
}