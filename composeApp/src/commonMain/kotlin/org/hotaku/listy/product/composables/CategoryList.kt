package org.hotaku.listy.product.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.core.presentation.composables.HorizontalSpacer_16dp

@Composable
fun CategoryList(
    categories: List<UiCategory>,
    selectedCategory: Int?,
    onCategorySelected: (Int) -> Unit,
    onAddNewCategory: () -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { HorizontalSpacer_16dp() }
        items(
            count = categories.size,
            key = { index -> categories[index] },
        ) { index ->
            CategoryItem(
                category = categories[index],
                isSelected = selectedCategory == index,
                onClick = { onCategorySelected(index) }
            )
        }
        item {
            AddCategory(
                onClick = onAddNewCategory
            )
        }
    }
}


@Composable
private fun AddCategory(
    onClick: () -> Unit,
) {
    OutlinedCard(
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CategoryItem(
    category: UiCategory,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    val cardColors = CardDefaults.outlinedCardColors(
        containerColor = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            CardDefaults.outlinedCardColors().containerColor
        },
        contentColor = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            CardDefaults.outlinedCardColors().contentColor
        }
    )

    OutlinedCard(
        onClick = onClick,
        colors = cardColors
    ) {
        Text(
            text = category.name,
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

