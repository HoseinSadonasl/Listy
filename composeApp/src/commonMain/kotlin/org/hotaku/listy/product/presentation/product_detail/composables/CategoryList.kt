package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_category_chip_label
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.brightBackgroundGray
import org.hotaku.listy.core.presentation.brightBlue
import org.hotaku.listy.core.presentation.brightGray
import org.hotaku.listy.core.presentation.composables.HorizontalSpacer_16dp
import org.hotaku.listy.core.presentation.gray
import org.hotaku.listy.core.presentation.grayText
import org.hotaku.listy.core.presentation.primaryBlue
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryList(
    categories: List<UiCategory>,
    selectedCategory: Int?,
    onCategorySelected: (Int) -> Unit,
    onAddNewCategory: () -> Unit,
    onEditCategoryClick: (Int) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item { HorizontalSpacer_16dp() }
        items(
            count = categories.size,
            key = { index -> categories[index].id!! },
        ) { index ->
            CategoryItem(
                category = categories[index],
                isSelected = selectedCategory == categories[index].id,
                onClick = { onCategorySelected(categories[index].id!!) },
                onEditClick = { onEditCategoryClick(categories[index].id!!) }
            )
        }
        item {
            AddCategory(
                onClick = onAddNewCategory
            )
        }
        item { HorizontalSpacer_16dp() }
    }
}


@Composable
private fun AddCategory(
    onClick: () -> Unit,
) {
    val colors = AssistChipDefaults.assistChipColors(
        containerColor = brightGray,
        labelColor = grayText,
        leadingIconContentColor = grayText
    )

    AssistChip(
        modifier = Modifier.height(40.dp),
        onClick = onClick,
        label = {
            Text(
                text = stringResource(Res.string.add_category_chip_label),
            )
        },
        colors = colors,
        border = null,
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
private fun AddCategoryPreview() {
    AddCategory(
        onClick = {}
    )
}

@Composable
private fun CategoryItem(
    category: UiCategory,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    onEditClick: () -> Unit,
) {
        val colors = FilterChipDefaults.filterChipColors(
            containerColor = brightBackgroundGray,
            selectedContainerColor = brightBlue,
            labelColor = grayText,
            selectedLabelColor = primaryBlue,
            selectedTrailingIconColor = primaryBlue,
        )

        FilterChip(
            modifier = Modifier.height(40.dp),
            selected = isSelected,
            onClick = onClick,
            border = null,
            label = {
                Text(
                    text = category.name,
                )
            },
            colors = colors,
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onEditClick() }
                    )
                }
            },
        )
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = UiCategory(
            id = 1,
            name = "Category 1",
        ),
        isSelected = true,
        onClick = {},
        onEditClick = {}
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
    Surface(
        color = background,
    ) {
        CategoryList(
            categories = categories,
            selectedCategory = 1,
            onCategorySelected = {},
            onAddNewCategory = {},
            onEditCategoryClick = {}
        )
    }
}