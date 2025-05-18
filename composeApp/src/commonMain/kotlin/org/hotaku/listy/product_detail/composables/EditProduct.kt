package org.hotaku.listy.product_detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
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
import listy.composeapp.generated.resources.all_button_delete
import listy.composeapp.generated.resources.all_button_save
import listy.composeapp.generated.resources.product_screen_complete
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.brightPink
import org.hotaku.listy.core.presentation.composables.SolidButton
import org.hotaku.listy.core.presentation.composables.TextInput
import org.hotaku.listy.products_list.presentation.UiProduct
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
    onDelete: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
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
            modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
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
                text = stringResource(Res.string.product_screen_complete)
            )
            Checkbox(
                checked = product?.done == true,
                onCheckedChange = { onDoneChange() }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            product?.let {
                SolidButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(Res.string.all_button_delete),
                    color = brightPink,
                    onClick = onDelete,
                )
            }
            SolidButton(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.all_button_save),
                onClick = onSaveProduct,
            )
        }
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
                description = "Item description",
                categoryId = 1,
                done = true,
                dateCreated = System.now(),
            ),
            onTitleChange = {},
            onDescriptionChange = {},
            onSaveProduct = {},
            onDoneChange = {},
            onDelete = {}
        )
    }
}