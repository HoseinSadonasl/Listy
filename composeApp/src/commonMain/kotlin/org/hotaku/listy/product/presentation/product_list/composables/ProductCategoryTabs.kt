package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_list_screen_all
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.brightGray
import org.hotaku.listy.core.presentation.grayText
import org.hotaku.listy.core.presentation.primaryBlue
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProductsCategoriesTabs(
    modifier: Modifier = Modifier,
    tabs: List<UiCategory>,
    selectedTabId: Int,
    onTabClick: (Int) -> Unit,
) {
    ScrollableTabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabId,
        containerColor = background,
        contentColor = primaryBlue,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabId]),
                color = primaryBlue,
                height = 2.dp
            )
        },
        divider = {
            HorizontalDivider(
                color = brightGray,
                thickness = 1.dp
            )
        },
        tabs = {
            CategoryTab(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(Res.string.product_list_screen_all),
                selected = selectedTabId == 0,
                onTabClick = { onTabClick(0) }
            )
            tabs.forEachIndexed { index, category ->
                CategoryTab(
                    title = category.name,
                    selected = selectedTabId == category.id,
                    onTabClick = { onTabClick(category.id!!) }
                )
            }
        },
    )
}

@Composable
private fun CategoryTab(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean = false,
    onTabClick: () -> Unit
) {
    Tab(
        modifier = modifier,
        text = { Text(text = title) },
        selected = selected,
        selectedContentColor = primaryBlue,
        unselectedContentColor = grayText,
        onClick = onTabClick
    )
}

@Preview
@Composable
private fun ProductsCategoriesTabsPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .padding(16.dp)
    ) {
        ProductsCategoriesTabs(
            tabs = listOf(
                UiCategory(id = 1, name = "Category 1"),
                UiCategory(id = 2, name = "Category 2"),
                UiCategory(id = 3, name = "Category 3")
            ),
            selectedTabId = 0,
            onTabClick = {}
        )
    }
}