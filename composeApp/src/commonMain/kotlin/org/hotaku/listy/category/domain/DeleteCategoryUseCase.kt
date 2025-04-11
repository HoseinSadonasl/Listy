package org.hotaku.listy.category.domain

class DeleteCategoryUseCase(
    private val categoriesRepository: CategoreisRepository,
) {
    suspend operator fun invoke(category: Category) = categoriesRepository.deleteCateGory(category = category)
}