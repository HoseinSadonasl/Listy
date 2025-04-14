package org.hotaku.listy.category.domain.usecases

import org.hotaku.listy.category.domain.repository.CategoreisRepository

class GetCategoriesUseCase(
    private val categoriesRepository: CategoreisRepository,
) {
    operator fun invoke() = categoriesRepository.getCategories()
}