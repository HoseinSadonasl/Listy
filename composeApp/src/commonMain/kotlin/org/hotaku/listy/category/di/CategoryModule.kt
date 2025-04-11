package org.hotaku.listy.category.di

import org.hotaku.listy.category.data.CategoryRepositoryImpl
import org.hotaku.listy.category.domain.CategoryRepository
import org.koin.dsl.module

val categoryModule = module {
     single<CategoryRepository> { CategoryRepositoryImpl(categoryDao = get()) }
}