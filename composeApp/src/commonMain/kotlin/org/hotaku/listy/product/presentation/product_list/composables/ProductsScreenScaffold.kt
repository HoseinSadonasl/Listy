package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.composables.TopRoundedCard
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.primaryBlue
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductListScreenScaffold(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(Res.string.products_screen_title),
            )
        },
        containerColor = primaryBlue,
        floatingActionButton = { AddItemFab(onClick = onAddClick) },
        content = { paddingValues ->
            TopRoundedCard(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                content = { content() }
            )
        },
    )
}

@Composable
private fun AddItemFab(
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = primaryBlue,
        contentColor = background,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    )
}