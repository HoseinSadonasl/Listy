package org.hotaku.listy.product.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_product_description_placeholder
import listy.composeapp.generated.resources.all_button_mark_as_done
import listy.composeapp.generated.resources.all_button_save
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.brightPink
import org.hotaku.listy.core.presentation.composables.SolidButton
import org.hotaku.listy.core.presentation.composables.TextInput
import org.hotaku.listy.product.presentation.UiProduct
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditProduct(
    modifier: Modifier = Modifier,
    product: UiProduct? = null,
    onEmojiChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveProduct: () -> Unit,
    onDelete: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextInput(
                value = product?.emoji ?: "\uD83D\uDECD\uFE0F",
                onValueChange = onEmojiChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = Center),
                modifier = Modifier.weight(.2f),
            )
            TextInput(
                value = product?.name.orEmpty(),
                onValueChange = onTitleChange,
                placeholder = stringResource(Res.string.products_screen_title),
                modifier = Modifier.weight(.8f),
            )
        }
        TextInput(
            value = product?.description.orEmpty(),
            onValueChange = onDescriptionChange,
            placeholder = stringResource(Res.string.add_product_description_placeholder),
            maxLines = 3,
            modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            product?.let {
                SolidButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(Res.string.all_button_mark_as_done),
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