package org.hotaku.listy.product.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.hotaku.listy.product.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val description: String,
    val categoryId: Int,
    val importance: String,
    val done: Boolean,
    val createdTimestamp: Long
)

fun ProductEntity.asProduct(): Product = Product(
    id = id,
    name = name,
    description = description,
    categoryId = categoryId,
    importance = importance,
    done = done,
    createdTimestamp = createdTimestamp,
)

fun Product.asEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    description = description,
    categoryId = categoryId,
    importance = importance,
    done = done,
    createdTimestamp = createdTimestamp,
)