package org.hotaku.listy.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.hotaku.listy.category.data.dao.CategoryDao
import org.hotaku.listy.category.data.CategoryEntity
import org.hotaku.listy.product.data.dao.ProductsDao
import org.hotaku.listy.product.data.ProductEntity

@Database(
    entities = [ProductEntity::class, CategoryEntity::class],
    version = 1, exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class ListyDataBase : RoomDatabase() {
    abstract val productsDao: ProductsDao
    abstract val categoryDao: CategoryDao

    companion object {
        const val DB_NAME = "listy.db"
    }
}