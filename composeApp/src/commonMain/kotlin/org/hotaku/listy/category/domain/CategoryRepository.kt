package org.hotaku.listy.category.domain

interface CategoryRepository {
    fun getCategories(): Result<List<Category>>
    fun addCateGory(category: Category)
    fun deleteCateGory(category: Category)
}