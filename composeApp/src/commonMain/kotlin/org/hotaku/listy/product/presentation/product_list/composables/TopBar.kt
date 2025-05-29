package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import org.hotaku.listy.core.presentation.composables.TopBarIconButton
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.grayText
import org.hotaku.listy.core.presentation.primaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
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