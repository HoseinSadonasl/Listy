package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.composables.HeaderText
import org.hotaku.listy.core.presentation.composables.TopBarIconButton
import org.hotaku.listy.core.presentation.grayText
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            HeaderText(text = title)
        },
        navigationIcon = {
            onNavigationClick?.let {
                TopBarIconButton(
                    onClick = it,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = background,
            navigationIconContentColor = grayText,
            actionIconContentColor = grayText,
            titleContentColor = grayText
        ),
        actions = actions
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        title = "Products",
        onNavigationClick = {},
        actions = {
            TopBarIconButton(
                onClick = {},
            )
        }
    )
}