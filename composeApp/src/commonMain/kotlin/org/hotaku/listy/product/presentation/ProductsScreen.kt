package org.hotaku.listy.product.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.hotaku.listy.product.presentation.composables.ProductsScreenScaffold
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductsScreenContent(
        state = state,
        onIntent = viewModel::onIntent
    )
}

@Composable
fun ProductsScreenContent(
    modifier: Modifier = Modifier,
    state: ProductScreenState,
    onIntent: (ProductsScreenIntents) -> Unit
) {
    ProductsScreenScaffold(
        onAddClick = { onIntent(ProductsScreenIntents.OnAddItemClick) },
        content = {

        }
    )
}