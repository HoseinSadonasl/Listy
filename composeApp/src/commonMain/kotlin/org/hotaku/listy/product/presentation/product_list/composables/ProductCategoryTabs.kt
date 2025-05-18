package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.core.presentation.brightGreen

@Composable
fun ProductsCategoriesTabs(
    modifier: Modifier = Modifier,
    tabs: List<UiCategory>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        tabs = {
            tabs.forEachIndexed { index, category ->
                CategoryTab(
                    title = category.name,
                    selected = selectedTabIndex == index,
                    onTabClick = { onTabClick(index) }
                )
            }
            CategoryTab(
                title = "All",
                selected = selectedTabIndex == tabs.size,
                onTabClick = onAddClick
            )
        },
    )
}

@Composable
private fun CategoryTab(
    title: String,
    selected: Boolean = false,
    onTabClick: () -> Unit
) {
    Tab(
        text = { Text(text = title) },
        selected = selected,
        selectedContentColor = brightGreen,
        onClick = onTabClick
    )
}