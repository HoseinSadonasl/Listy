package org.hotaku.listy.product.presentation.product_detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_screen_title
import org.hotaku.listy.core.presentation.brightGreen
import org.hotaku.listy.core.presentation.powder
import org.hotaku.listy.product.presentation.product_list.composables.TopBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductDetailScreenScaffold(
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
        contentColor = powder,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors().copy(
                        containerColor = powder,
                    ),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp,
                    )
                ) {
                    content()
                }
            }
        },
    )
}