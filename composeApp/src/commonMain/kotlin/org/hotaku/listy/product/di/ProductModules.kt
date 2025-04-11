package org.hotaku.listy.product.di

import org.hotaku.listy.product.data.ProductsRepositoryImpl
import org.hotaku.listy.product.domain.ProductsRepository
import org.hotaku.listy.product.presentation.ProductsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val productsModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl(productsDao = get()) }
    singleOf(::ProductsViewModel)
}