package org.hotaku.listy.category.domain.usecases

import org.hotaku.listy.category.domain.model.Category
import org.hotaku.listy.category.domain.repository.CategoreisRepository

class DeleteCategoryUseCase(
    private val categoriesRepository: CategoreisRepository,
) {
    suspend operator fun invoke(category: Category) = categoriesRepository.deleteCateGory(category = category)
}