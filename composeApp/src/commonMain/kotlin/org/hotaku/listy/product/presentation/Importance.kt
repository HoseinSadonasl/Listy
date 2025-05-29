package org.hotaku.listy.product.presentation

import androidx.compose.ui.graphics.Color
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.category_essentials
import listy.composeapp.generated.resources.category_essentials_description
import listy.composeapp.generated.resources.category_important
import listy.composeapp.generated.resources.category_important_description
import listy.composeapp.generated.resources.category_wants
import listy.composeapp.generated.resources.category_wants_description
import org.hotaku.listy.core.presentation.essential
import org.hotaku.listy.core.presentation.important
import org.hotaku.listy.core.presentation.wants
import org.jetbrains.compose.resources.StringResource

data class Importance(
    val importance: ImportanceEnum,
    val title: StringResource,
    val description: StringResource,
    val color: Color,
)

enum class ImportanceEnum {
    ESSENTIAL,
    IMPORTANT,
    WANTS;
}

val productImportance = listOf(
    Importance(
        importance = ImportanceEnum.ESSENTIAL,
        title = Res.string.category_essentials,
        description = Res.string.category_essentials_description,
        color = essential,
    ),
    Importance(
        importance = ImportanceEnum.IMPORTANT,
        title = Res.string.category_important,
        description = Res.string.category_important_description,
        color = important,
    ),
    Importance(
        importance = ImportanceEnum.WANTS,
        title = Res.string.category_wants,
        description = Res.string.category_wants_description,
        color = wants,
    ),
)