package org.hotaku.listy.product.presentation.product_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.Clock
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_16dp
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.NavigateBack
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDeleteClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDoneClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnNewCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailDescriptionChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailNameChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveClick
import org.hotaku.listy.product.presentation.product_detail.composables.CategoryList
import org.hotaku.listy.product.presentation.product_detail.composables.EditProduct
import org.hotaku.listy.product.presentation.product_detail.composables.ProductDetailScreenScaffold
import org.hotaku.listy.product.presentation.product_detail.composables.categories
import org.hotaku.listy.product.presentation.UiProduct
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is NavigateBack -> onBackClick()
            }
        }
    }

    ProductDetailScreenContent(
        state = state,
        onBackClick = onBackClick,
        onIntent = viewModel::onIntent,
    )
}

@Composable
fun ProductDetailScreenContent(
    state: ProductDetailScreenState,
    onBackClick: () -> Unit,
    onIntent: (ProductDetailScreenIntent) -> Unit,
) {
    ProductDetailScreenScaffold(
        title = state.product?.name,
        onNavigateBack = onBackClick,
        content = {
            VerticalSpacer_16dp()
            CategoryList(
                categories = state.categories,
                selectedCategory = state.product?.categoryId,
                onCategorySelected = { categoryId ->

                },
                onAddNewCategory = { onIntent(OnNewCategory) }
            )

            EditProduct(
                modifier = Modifier.padding(16.dp),
                onTitleChange = { name ->
                    onIntent(OnProductDetailNameChanged(name = name))
                },
                onDescriptionChange = { description ->
                    onIntent(OnProductDetailDescriptionChanged(description = description))

                },
                onSaveProduct = { onIntent(OnSaveClick) },
                onDelete = { onIntent(OnDeleteClick) },
                product = state.product,
                onDoneChange = { onIntent(OnDoneClick) }
            )
        },
    )
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductDetailScreenContent(
        state = ProductDetailScreenState(
            product = UiProduct(
                id = 1,
                name = "Product 1",
                description = "Description 1",
                done = false,
                categoryId = 2,
                dateCreated = Clock.System.now(),
            ),
            categories = categories,
            isLoading = false,
        ),
        onBackClick = {},
        onIntent = {},
    )
}