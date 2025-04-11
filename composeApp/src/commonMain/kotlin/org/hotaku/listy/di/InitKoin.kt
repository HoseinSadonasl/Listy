package org.hotaku.listy.di

import org.hotaku.listy.category.di.categoryModule
import org.hotaku.listy.product.di.productsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule,
            commonModule,
            productsModule,
            categoryModule,
        )
    }
}