package org.hotaku.listy.di

import org.hotaku.listy.core.database.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { DatabaseFactory(context = androidApplication()) }
}