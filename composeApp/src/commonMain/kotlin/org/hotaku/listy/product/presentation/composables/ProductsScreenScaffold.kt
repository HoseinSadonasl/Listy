package org.hotaku.listy.product.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_item
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.brightGreen
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

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
                    .verticalScroll(rememberScrollState())
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
                imageVector = vectorResource(Res.drawable.add_item),
                contentDescription = null
            )
        }
    )
}