package org.hotaku.listy.di

import org.hotaku.listy.core.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::DatabaseFactory)
}