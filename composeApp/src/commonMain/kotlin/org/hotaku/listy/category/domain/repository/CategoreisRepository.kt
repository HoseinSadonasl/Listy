package org.hotaku.listy.category.domain.repository

import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.category.domain.model.Category

interface CategoreisRepository {
    fun getCategories(): Flow<List<Category>>
    suspend fun upsertCategory(category: Category)
    suspend fun deleteCateGory(category: Category)
}