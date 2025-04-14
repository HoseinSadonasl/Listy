package org.hotaku.listy.category.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.hotaku.listy.category.domain.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
)

fun CategoryEntity.asCategory(): Category = Category(
    id = id,
    name = name,
)

fun Category.asEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
)