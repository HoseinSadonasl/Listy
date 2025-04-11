package org.hotaku.listy.category.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.hotaku.listy.category.data.CategoryEntity

@Dao
interface CategoryDao {

    @Upsert
    suspend fun upsertCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

}