package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.hotaku.listy.core.presentation.brightBackgroundGray
import org.hotaku.listy.core.presentation.composables.DefaultCard
import org.hotaku.listy.core.presentation.grayText
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
        cardColor = brightBackgroundGray,
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
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(color.copy(alpha = .2f))
                        .padding(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(RoundedCornerShape(percent = 50))
                            .background(color).padding(8.dp)
                    )
                }
                Text(
                    text = category,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = grayText,
                )
            }
            Text(
                text = description,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = grayTextUnFocused,
                maxLines = 2,
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