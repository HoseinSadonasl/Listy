package org.hotaku.listy.product.di

import org.hotaku.listy.product.presentation.ProductsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelsModule = module {
    singleOf(::ProductsViewModel)
}