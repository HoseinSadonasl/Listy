package org.hotaku.listy.category.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.hotaku.listy.category.data.dao.CategoryDao
import org.hotaku.listy.category.domain.Category
import org.hotaku.listy.category.domain.CategoreisRepository

class CategoreisRepositoryImpl(
    private val categoryDao: CategoryDao,
): CategoreisRepository {
    override fun getCategories(): Flow<List<Category>> =
        categoryDao.getCategories().map { categories ->
            categories.map { it.asCategory() }
        }

    override suspend fun addCateGory(category: Category) =
        categoryDao.upsertCategory(category = category.asEntity())

    override suspend fun deleteCateGory(category: Category) =
        categoryDao.deleteCategory(category = category.asEntity())
}