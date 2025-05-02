package org.hotaku.listy.category.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.hotaku.listy.category.data.dao.CategoryDao
import org.hotaku.listy.category.data.model.asCategory
import org.hotaku.listy.category.data.model.asEntity
import org.hotaku.listy.category.domain.repository.CategoreisRepository
import org.hotaku.listy.category.domain.model.Category

class CategoreisRepositoryImpl(
    private val categoryDao: CategoryDao,
): CategoreisRepository {
    override fun getCategories(): Flow<List<Category>> =
        categoryDao.getCategories().map { categories ->
            categories.map { it.asCategory() }
        }

    override suspend fun upsertCategory(category: Category) =
        categoryDao.upsertCategory(category = category.asEntity())

    override suspend fun deleteCateGory(category: Category) =
        categoryDao.deleteCategory(category = category.asEntity())
}