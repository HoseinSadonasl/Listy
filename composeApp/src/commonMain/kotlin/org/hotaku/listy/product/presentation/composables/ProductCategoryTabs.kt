package org.hotaku.listy.product.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hotaku.listy.category.presentation.UiCategory

@Composable
fun ProductsCategoriesTabs(
    modifier: Modifier = Modifier,
    tabs: List<UiCategory>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
) {
    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        tabs = {
            tabs.forEachIndexed { index, category ->
                Tab(
                    text = { Text(text = category.name) },
                    selected = selectedTabIndex == index,
                    onClick = { onTabClick(index) }
                )
            }
        },
    )
}