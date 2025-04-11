package org.hotaku.listy.category.domain

class AddCategoryUseCase(
    private val categoriesRepository: CategoreisRepository,
) {
    suspend operator fun invoke(category: Category) = categoriesRepository.addCateGory(category = category)
}