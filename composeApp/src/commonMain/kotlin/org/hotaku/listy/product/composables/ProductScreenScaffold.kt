package org.hotaku.listy.product.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_screen_title
import listy.composeapp.generated.resources.products_screen_title
import org.hotaku.listy.core.presentation.brightGreen
import org.hotaku.listy.core.presentation.composables.IconButton
import org.hotaku.listy.products_list.presentation.composables.TopBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductScreenScaffold(
    modifier: Modifier = Modifier,
    title: String? = null,
    onNavigateBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = title ?: stringResource(Res.string.product_screen_title),
                onNavigationClick = onNavigateBack,
            )
        },
        containerColor = brightGreen,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                content()
            }
        },
    )
}