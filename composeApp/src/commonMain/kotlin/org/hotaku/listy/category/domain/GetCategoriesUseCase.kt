package org.hotaku.listy.category.domain

class GetCategoriesUseCase(
    private val categoriesRepository: CategoreisRepository,
) {
    operator fun invoke() = categoriesRepository.getCategories()
}