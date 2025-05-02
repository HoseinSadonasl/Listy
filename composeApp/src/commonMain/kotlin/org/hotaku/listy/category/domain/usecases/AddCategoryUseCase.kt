package org.hotaku.listy.category.domain.usecases

import org.hotaku.listy.category.domain.model.Category
import org.hotaku.listy.category.domain.repository.CategoreisRepository

class AddCategoryUseCase(
    private val categoriesRepository: CategoreisRepository,
) {
    suspend operator fun invoke(category: Category) = categoriesRepository.upsertCategory(category = category)
}