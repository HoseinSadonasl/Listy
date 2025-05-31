package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock.System
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_product_description_placeholder
import listy.composeapp.generated.resources.all_button_save
import listy.composeapp.generated.resources.product_screen_complete
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.brightBackgroundGray
import org.hotaku.listy.core.presentation.composables.BodyText
import org.hotaku.listy.core.presentation.composables.CheckBox
import org.hotaku.listy.core.presentation.composables.DefaultCard
import org.hotaku.listy.core.presentation.composables.SolidButton
import org.hotaku.listy.core.presentation.composables.TextInput
import org.hotaku.listy.product.presentation.Importance
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.productImportance
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EditProduct(
    modifier: Modifier = Modifier,
    product: UiProduct?,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    productImportance: Importance,
    importanceExpended: Boolean,
    onImportanceChanged: (Importance) -> Unit,
    onImportanceClick: () -> Unit,
    onImportanceDismissRequest: () -> Unit,
    onSaveProduct: () -> Unit,
    onDoneChange: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextInput(
            value = product?.name.orEmpty(),
            onValueChange = onTitleChange,
            placeholder = stringResource(Res.string.products_screen_title),
            modifier = Modifier.fillMaxWidth(),
        )
        TextInput(
            value = product?.description.orEmpty(),
            onValueChange = onDescriptionChange,
            placeholder = stringResource(Res.string.add_product_description_placeholder),
            maxLines = 3,
            singleLine = false,
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 112.dp),
        )
        DefaultCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onImportanceClick() },
            cardColor = brightBackgroundGray
        ) {
            ImportanceCategory(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                expanded = importanceExpended,
                importance = productImportance,
                onImportanceChange = onImportanceChanged,
                onDismissRequest = onImportanceDismissRequest,
            )
        }
        DefaultCard(
            cardColor = brightBackgroundGray
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BodyText(
                    text = stringResource(Res.string.product_screen_complete),
                )
                CheckBox(
                    isChecked = product?.done == true,
                    onCheckedChange = { onDoneChange() }
                )
            }
        }
        SolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.all_button_save),
            onClick = onSaveProduct,
        )
    }
}

@Preview
@Composable
fun EditProductPreview() {
    Box(
        modifier = Modifier.fillMaxWidth().background(background).padding(16.dp)
    ) {
        EditProduct(
            product = UiProduct(
                id = 1,
                name = "Item name",
                description = "Item description \nItem description \nItem description",
                categoryId = 1,
                done = true,
                importance = productImportance.first(),
                dateCreated = System.now(),
            ),
            onTitleChange = { },
            onDescriptionChange = { },
            productImportance = productImportance.first(),
            importanceExpended = true,
            onImportanceChanged = {},
            onImportanceClick = { },
            onImportanceDismissRequest = { },
            onSaveProduct = { },
            onDoneChange = { },
        )
    }
}