package org.hotaku.listy.category.domain

import kotlinx.coroutines.flow.Flow

interface CategoreisRepository {
    fun getCategories(): Flow<List<Category>>
    suspend fun addCateGory(category: Category)
    suspend fun deleteCateGory(category: Category)
}