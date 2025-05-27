package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_listy
import listy.composeapp.generated.resources.product_screen_title
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.composables.SnackBar
import org.hotaku.listy.core.presentation.composables.TopBarIconButton
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_16dp
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.product.presentation.product_list.composables.TopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ProductDetailScreenScaffold(
    modifier: Modifier = Modifier,
    title: String? = null,
    snackbarHostState: SnackbarHostState,
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
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(32.dp),
                snackbar = { SnackBar(message = it.visuals.message,) }
            )
        },
        containerColor = primaryBlue,
        contentColor = background,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .aspectRatio(1f),
                        imageVector = vectorResource(Res.drawable.add_listy),
                        contentDescription = null,
                    )
                }
                VerticalSpacer_16dp()
                content()
            }
        },
    )
}