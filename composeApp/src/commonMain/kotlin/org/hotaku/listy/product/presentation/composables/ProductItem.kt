package org.hotaku.listy.product.presentation.composables

import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hotaku.listy.product.presentation.UiProduct

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: UiProduct,
    onDoneChange: (Boolean) -> Unit,
) {
    ListItem(
        headlineContent =  {
            Text(
                text = product.name,
            )
        },
        modifier = modifier,
        overlineContent = {
            Text(
                text = product.description,
            )
        },
        leadingContent =  {
            Text(
                text = product.emoji,
            )
        },
        trailingContent =  {
            Checkbox(
                checked = product.done,
                onCheckedChange = { onDoneChange(!product.done) },
            )
        },
    )
}