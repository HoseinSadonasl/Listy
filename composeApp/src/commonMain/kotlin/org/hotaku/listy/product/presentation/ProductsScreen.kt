package org.hotaku.listy.product.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductsScreenContent(
        state = state
    )
}

@Composable
fun ProductsScreenContent(
    modifier: Modifier = Modifier,
    state: ProductScreenState
) {

}