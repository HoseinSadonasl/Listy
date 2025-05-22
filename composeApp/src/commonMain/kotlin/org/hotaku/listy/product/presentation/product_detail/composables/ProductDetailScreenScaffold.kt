package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_screen_title
import org.hotaku.listy.core.presentation.composables.TopBarIconButton
import org.hotaku.listy.core.presentation.powder
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.product.presentation.product_list.composables.TopBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductDetailScreenScaffold(
    modifier: Modifier = Modifier,
    title: String? = null,
    onNavigateBack: () -> Unit,
    onDeleteClick: (() -> Unit)?,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = title ?: stringResource(Res.string.product_screen_title),
                onNavigationClick = onNavigateBack,
                actions = {
                    onDeleteClick?.let {
                        TopBarIconButton(
                            onClick = it,
                            icon = Icons.Outlined.Delete,
                        )
                    }
                }
            )
        },
        containerColor = primaryBlue,
        contentColor = powder,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                content()
            }
        },
    )
}