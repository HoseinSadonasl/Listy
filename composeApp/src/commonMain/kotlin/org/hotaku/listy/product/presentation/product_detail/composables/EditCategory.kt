package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_screen_new_category_placeholder
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.core.presentation.composables.HorizontalSpacer_16dp
import org.hotaku.listy.core.presentation.composables.HorizontalSpacer_8dp
import org.hotaku.listy.core.presentation.composables.SolidIconButton
import org.hotaku.listy.core.presentation.composables.TextInput
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EditCategory(
    modifier: Modifier = Modifier,
    category: UiCategory?,
    onCategoryNameChange: (String) -> Unit,
    onSaveClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextInput(
            modifier = Modifier.weight(1f),
            value = category?.name.orEmpty(),
            placeholder = stringResource(Res.string.product_screen_new_category_placeholder),
            onValueChange = { onCategoryNameChange(it) }
        )
        HorizontalSpacer_16dp()
        SolidIconButton(
            onClick = onSaveClick
        )
    }
}

@Preview
@Composable
fun EditCategoryPreview() {
    Surface {
        EditCategory(
            category = UiCategory(
                name = "Test Category",
            ),
            onCategoryNameChange = {},
            onSaveClick = {}
        )
    }
}