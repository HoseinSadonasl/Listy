package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.edit_product_category_importance
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.composables.HorizontalSpacer_8dp
import org.hotaku.listy.core.presentation.composables.ImportanceDot
import org.hotaku.listy.core.presentation.grayText
import org.hotaku.listy.product.presentation.Importance
import org.hotaku.listy.product.presentation.productImportance
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ImportanceCategory(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    importance: Importance,
    onImportanceChange: (Importance) -> Unit,
    onDismissRequest: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.edit_product_category_importance),
            color = grayText,
        )
        Box(
            contentAlignment = Alignment.CenterEnd,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ImportanceDot(color = importance.color)
                HorizontalSpacer_8dp()
                Text(
                    text = stringResource(importance.title),
                    color = importance.color,
                )
            }
            DropdownMenu(
                expanded = expanded,
                shape = RoundedCornerShape(16.dp),
                containerColor = background,
                onDismissRequest = onDismissRequest,
                shadowElevation = 12.dp
            ) {
                productImportance.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(item.title),
                                color = grayText
                            )
                        },
                        leadingIcon = {
                            ImportanceDot(color = item.color)
                        },
                        onClick = {
                            onImportanceChange(item)
                            onDismissRequest()
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ImportanceCategoryPreview() {
    Box(
        modifier = Modifier.fillMaxWidth().background(background).padding(16.dp)
    ) {
        ImportanceCategory(
            expanded = true,
            importance = productImportance[0],
            onImportanceChange = {},
            onDismissRequest = {}
        )
    }
}