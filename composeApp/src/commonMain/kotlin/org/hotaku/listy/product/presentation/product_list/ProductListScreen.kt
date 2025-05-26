package org.hotaku.listy.product.presentation.product_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.empty_listy
import listy.composeapp.generated.resources.empty_state_error_message
import listy.composeapp.generated.resources.load_data_error
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.product_detail.composables.categories
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnDoneClick
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnNewProduct
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnProductItemClick
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnTabChange
import org.hotaku.listy.product.presentation.product_list.ProductListScreenIntent.OnUpdateList
import org.hotaku.listy.product.presentation.product_list.composables.Message
import org.hotaku.listy.product.presentation.product_list.composables.ProductListScreenScaffold
import org.hotaku.listy.product.presentation.product_list.composables.ProductsCategoriesTabs
import org.hotaku.listy.product.presentation.product_list.composables.ProductsList
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
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

    val pagerState = rememberPagerState(pageCount = {
        state.categories.size
    })

    LaunchedEffect(pagerState.currentPage) {
        onIntent(OnTabChange(tabIndex = pagerState.currentPage))
    }

    LaunchedEffect(state.selectedCategory) {
        onIntent(OnUpdateList)
    }

    ProductListScreenScaffold(
        modifier = modifier,
        onAddClick = { onIntent(OnNewProduct) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (state.error != null) {
                    Message(
                        message = stringResource(Res.string.load_data_error, state.error)
                    )
                } else {
                    ProductsCategoriesTabs(
                        tabs = state.categories,
                        selectedTabId = state.selectedCategory,
                        onTabClick = { onIntent(OnTabChange(tabIndex = it)) },
                    )

                    HorizontalPager(
                        state = pagerState
                    ) { index ->
                        if (state.isLoading) {
                            LoadingState()
                        } else {
                            SucessState(products = state.products, onIntent = onIntent)
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun SucessState(
    products: List<UiProduct>,
    onIntent: (ProductListScreenIntent) -> Unit
) {
    if (products.isNotEmpty()) {
        ProductsList(
            products = products,
            onProductItemClick = { productId ->
                onIntent(
                    OnProductItemClick(
                        productId = productId
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
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Message(
                vectorRes = vectorResource(Res.drawable.empty_listy),
                message = stringResource(Res.string.empty_state_error_message)
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = primaryBlue,
            strokeWidth = 2.dp,
            strokeCap = StrokeCap.Round
        )
    }
}

val products = listOf<UiProduct>(
    UiProduct(
        id = 1,
        name = "Product 1",
        description = "Description of Product 1",
        categoryId = 1,
        done = false,
        dateCreated = kotlinx.datetime.Clock.System.now()
    ),
    UiProduct(
        id = 2,
        name = "Product 2",
        description = "Description of Product 2",
        categoryId = 2,
        done = true,
        dateCreated = kotlinx.datetime.Clock.System.now()
    ),
    UiProduct(
        id = 3,
        name = "Product 3",
        description = "Description of Product 3",
        categoryId = 3,
        done = false,
        dateCreated = kotlinx.datetime.Clock.System.now()
    )
)

@Preview
@Composable
private fun ProductListScreenPreview() {
    ProductListScreenContent(
        state = ProductListScreenState(
            isLoading = false,
            error = null,
            categories = categories,
            selectedCategory = 0,
            products = products
        ),
        onIntent = {}
    )
}
