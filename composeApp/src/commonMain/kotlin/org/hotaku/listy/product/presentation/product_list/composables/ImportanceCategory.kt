package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.cardBrightBackgroundGray
import org.hotaku.listy.core.presentation.composables.BodyText
import org.hotaku.listy.core.presentation.composables.CaptionText
import org.hotaku.listy.core.presentation.composables.DefaultCard
import org.hotaku.listy.core.presentation.composables.ImportanceDot
import org.hotaku.listy.core.presentation.grayTextUnFocused
import org.hotaku.listy.product.presentation.productImportance
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ImportanceCategory(
    modifier: Modifier = Modifier,
    category: String,
    description: String,
    selected: Boolean = false,
    color: Color,
    onClick: () -> Unit,
) {
    DefaultCard(
        cardColor = cardBrightBackgroundGray,
        borderColor = if (selected) color else null,
    ) {
        Column(
            modifier = modifier
                .clickable { onClick() }
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImportanceDot(color = color)
                BodyText(
                    text = category,
                    bold = true
                )
            }
            CaptionText(
                text = description,
                maxLine = 2,
                color = grayTextUnFocused,
            )
        }
    }
}



@Preview
@Composable
private fun CategoryPreview() {
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        productImportance.forEach { entry ->
            ImportanceCategory(
                modifier = Modifier.width(140.dp),
                category = stringResource(entry.title),
                description = stringResource(entry.description),
                color = entry.color,
                onClick = {}
            )
        }
    }
}