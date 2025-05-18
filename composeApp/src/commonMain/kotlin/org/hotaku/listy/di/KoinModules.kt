package org.hotaku.listy.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.hotaku.listy.category.data.dao.CategoryDao
import org.hotaku.listy.core.database.DatabaseFactory
import org.hotaku.listy.core.database.ListyDataBase
import org.hotaku.listy.product.data.dao.ProductsDao
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single<ProductsDao> { get<ListyDataBase>().productsDao }
    single<CategoryDao> { get<ListyDataBase>().categoryDao }

}