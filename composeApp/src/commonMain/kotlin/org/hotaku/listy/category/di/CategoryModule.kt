package org.hotaku.listy.category.di

import org.hotaku.listy.category.data.repository.CategoreisRepositoryImpl
import org.hotaku.listy.category.domain.usecases.AddCategoryUseCase
import org.hotaku.listy.category.domain.repository.CategoreisRepository
import org.hotaku.listy.category.domain.usecases.DeleteCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val categoryModule = module {
     single<CategoreisRepository> { CategoreisRepositoryImpl(categoryDao = get()) }

     singleOf(::GetCategoriesUseCase)
     singleOf(::AddCategoryUseCase)
     singleOf(::DeleteCategoryUseCase)
}