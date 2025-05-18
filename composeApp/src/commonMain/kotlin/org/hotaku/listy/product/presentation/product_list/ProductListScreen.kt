package org.hotaku.listy.product.presentation.product_list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.load_data_error
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnDoneClick
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnNewProduct
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnProductItemClick
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnTabClick
import org.hotaku.listy.product.presentation.product_list.composables.ErrorState
import org.hotaku.listy.product.presentation.product_list.composables.LoadingState
import org.hotaku.listy.product.presentation.product_list.composables.ProductListScreenScaffold
import org.hotaku.listy.product.presentation.product_list.composables.ProductsCategoriesTabs
import org.hotaku.listy.product.presentation.product_list.composables.ProductsList
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = koinViewModel(),
    navigateToProductScreen: (Int?) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is ProductListScreenEvent.NavigateToProductScreen -> {
                    navigateToProductScreen(event.productId)
                }
            }
        }
    }

    ProductListScreenContent(
        state = state,
        onIntent = viewModel::onIntent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreenContent(
    modifier: Modifier = Modifier,
    state: ProductListScreenState,
    onIntent: (ProductListScreenIntent) -> Unit
) {
    ProductListScreenScaffold(
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
                onAddClick = { }
            )
            ProductsList(
                products = state.products,
                onProductItemClick = { productId ->
                    onIntent(
                        OnProductItemClick(
                            productId = productId
                        )
                    )
                },
                onDoneClick = { productId ->
                    onIntent(
                        OnDoneClick(
                            productId = productId
                        )
                    )
                }
            )
        }
    )
}