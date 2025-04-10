package org.hotaku.listy.product.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.hotaku.listy.core.presentation.UiState
import org.hotaku.listy.core.presentation.UiText
import org.hotaku.listy.product.presentation.ProductsScreenIntents.*
import org.hotaku.listy.product.presentation.composables.ErrorState
import org.hotaku.listy.product.presentation.composables.LoadingState
import org.hotaku.listy.product.presentation.composables.ProductsCategoriesTabs
import org.hotaku.listy.product.presentation.composables.ProductsList
import org.hotaku.listy.product.presentation.composables.ProductsScreenScaffold
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = koinViewModel()
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
    state: UiState<ProductScreenState, UiText>,
    onIntent: (ProductsScreenIntents) -> Unit
) {
    ProductsScreenScaffold(
        modifier = modifier,
        onAddClick = { onIntent(OnAddItemClick) },
        content = {
            when (state) {
                is UiState.Error -> ErrorState(message = state.error)
                UiState.Idle -> { /* Do nothing */
                }

                UiState.Loading -> LoadingState()
                is UiState.Success -> {
                    ProductsCategoriesTabs(
                        tabs = state.data.categories,
                        selectedTabIndex = state.data.selectedTabIndex,
                        onTabClick = { onIntent(OnTabClick(tabIndex = it)) }
                    )
                    ProductsList(
                        products = state.data.products,
                        onDoneClick = { product ->
                            onIntent(
                                OnDoneClick(
                                    product = product
                                )
                            )
                        }
                    )
                }
            }
        }
    )
}