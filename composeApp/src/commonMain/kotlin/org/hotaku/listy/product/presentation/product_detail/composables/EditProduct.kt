package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock.System
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_product_description_placeholder
import listy.composeapp.generated.resources.all_button_save
import listy.composeapp.generated.resources.product_screen_complete
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.composables.CheckBox
import org.hotaku.listy.core.presentation.composables.SolidButton
import org.hotaku.listy.core.presentation.composables.TextInput
import org.hotaku.listy.product.presentation.UiProduct
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EditProduct(
    modifier: Modifier = Modifier,
    product: UiProduct? = null,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
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
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = if (product?.done == true) {
                    TextDecoration.LineThrough
                } else null
            )
        )
        TextInput(
            value = product?.description.orEmpty(),
            onValueChange = onDescriptionChange,
            placeholder = stringResource(Res.string.add_product_description_placeholder),
            maxLines = 3,
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 112.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = if (product?.done == true) {
                    TextDecoration.LineThrough
                } else null
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.product_screen_complete),
                style = MaterialTheme.typography.bodyLarge,
            )
            CheckBox(
                isChecked = product?.done == true,
                onCheckedChange = { onDoneChange() }
            )
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
    Surface {
        EditProduct(
            product = UiProduct(
                id = 1,
                name = "Item name",
                description = "Item description \nItem description \nItem description",
                categoryId = 1,
                done = true,
                dateCreated = System.now(),
            ),
            onTitleChange = {},
            onDescriptionChange = {},
            onSaveProduct = {},
            onDoneChange = {},
        )
    }
}