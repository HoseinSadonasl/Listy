package org.hotaku.listy.category.di

import org.hotaku.listy.category.data.CategoreisRepositoryImpl
import org.hotaku.listy.category.domain.AddCategoryUseCase
import org.hotaku.listy.category.domain.CategoreisRepository
import org.hotaku.listy.category.domain.DeleteCategoryUseCase
import org.hotaku.listy.category.domain.GetCategoriesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val categoryModule = module {
     single<CategoreisRepository> { CategoreisRepositoryImpl(categoryDao = get()) }

     singleOf(::GetCategoriesUseCase)
     singleOf(::AddCategoryUseCase)
     singleOf(::DeleteCategoryUseCase)
}