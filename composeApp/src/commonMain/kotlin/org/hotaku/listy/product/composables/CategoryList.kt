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
import org.hotaku.listy.core.presentation.brightGreen
import org.hotaku.listy.core.presentation.composables.HorizontalSpacer_8dp
import org.hotaku.listy.core.presentation.powder
import org.jetbrains.compose.ui.tooling.preview.Preview

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
        item { HorizontalSpacer_8dp() }
        items(
            count = categories.size,
            key = { index -> categories[index].id!! },
        ) { index ->
            CategoryItem(
                category = categories[index],
                isSelected = selectedCategory == categories[index].id,
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
    val cardColors = CardDefaults.outlinedCardColors(
        containerColor = powder,
    )
    OutlinedCard(
        onClick = onClick,
        colors = cardColors,
    ) {
        Box(
            modifier = Modifier.padding(12.dp),
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
            brightGreen
        } else {
            powder
        },
        contentColor = if (isSelected) {
            powder
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
                .padding(12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = UiCategory(
            id = 1,
            name = "Category 1",
        ),
        isSelected = false,
        onClick = {}
    )
}

val categories = listOf(
    UiCategory(
        id = 1,
        name = "Category 1",
    ),
    UiCategory(
        id = 2,
        name = "Category 2",
    ),
    UiCategory(
        id = 3,
        name = "Category 3",
    ),
)

@Preview
@Composable
fun CategoryListPreview() {
    CategoryList(
        categories = categories,
        selectedCategory = 2,
        onCategorySelected = {},
        onAddNewCategory = {}
    )
}