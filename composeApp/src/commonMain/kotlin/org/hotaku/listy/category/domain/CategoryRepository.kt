package org.hotaku.listy.category.domain

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    suspend fun addCateGory(category: Category)
    suspend fun deleteCateGory(category: Category)
}