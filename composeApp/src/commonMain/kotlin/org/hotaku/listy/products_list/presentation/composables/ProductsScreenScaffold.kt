package org.hotaku.listy.products_list.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.brightGreen
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductsScreenScaffold(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(Res.string.products_screen_title),
            )
        },
        containerColor = brightGreen,
        floatingActionButton = { AddItemFab(onClick = onAddClick) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                content()
            }
        },
    )
}

@Composable
private fun AddItemFab(
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = brightGreen,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    )
}