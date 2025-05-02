package org.hotaku.listy.products_list.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.load_data_error
import org.hotaku.listy.products_list.presentation.ProductsScreenIntents.*
import org.hotaku.listy.product.composables.EditProduct
import org.hotaku.listy.products_list.presentation.composables.ErrorState
import org.hotaku.listy.products_list.presentation.composables.LoadingState
import org.hotaku.listy.products_list.presentation.composables.ProductsCategoriesTabs
import org.hotaku.listy.products_list.presentation.composables.ProductsList
import org.hotaku.listy.products_list.presentation.composables.ProductsScreenScaffold
import org.jetbrains.compose.resources.stringResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreenContent(
    modifier: Modifier = Modifier,
    state: ProductScreenState,
    onIntent: (ProductsScreenIntents) -> Unit
) {
    ProductsScreenScaffold(
        modifier = modifier,
        onAddClick = { onIntent(OnNewProduct) },
        content = {
            if (state.isLoading) LoadingState()

            state.error?.let { errorMessage ->
                ErrorState(
                    message = stringResource(Res.string.load_data_error, errorMessage)
                )
            }

            ProductsCategoriesTabs(
                tabs = state.categories,
                selectedTabIndex = state.selectedTabIndex,
                onTabClick = { onIntent(OnTabClick(tabIndex = it)) },
                onAddClick = { onIntent(OnNewCategory) }
            )
            ProductsList(
                products = state.products,
                onProductItemClick = { product ->
                    onIntent(
                        OnOpenEditItemSheet(
                            product = product
                        )
                    )
                },
                onDoneClick = { product ->
                    onIntent(
                        OnDoneClick(
                            product = product
                        )
                    )
                }
            )
        }
    )
}