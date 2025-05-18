package org.hotaku.listy.product

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
import org.hotaku.listy.product.ProductScreenIntents.OnDeleteClick
import org.hotaku.listy.product.ProductScreenIntents.OnDoneClick
import org.hotaku.listy.product.ProductScreenIntents.OnNewCategory
import org.hotaku.listy.product.ProductScreenIntents.OnProductDescriptionChanged
import org.hotaku.listy.product.ProductScreenIntents.OnProductNameChanged
import org.hotaku.listy.product.ProductScreenIntents.OnSaveClick
import org.hotaku.listy.product.composables.CategoryList
import org.hotaku.listy.product.composables.EditProduct
import org.hotaku.listy.product.composables.ProductScreenScaffold
import org.hotaku.listy.product.composables.categories
import org.hotaku.listy.products_list.presentation.UiProduct
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ProductScreenEvents.NavigateBack -> onBackClick()
            }
        }
    }

    ProductScreenContent(
        state = state,
        onBackClick = onBackClick,
        onIntent = viewModel::onIntent,
    )
}

@Composable
fun ProductScreenContent(
    state: ProductScreenState,
    onBackClick: () -> Unit,
    onIntent: (ProductScreenIntents) -> Unit,
) {
    ProductScreenScaffold(
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
                    onIntent(OnProductNameChanged(name = name))
                },
                onDescriptionChange = { description ->
                    onIntent(OnProductDescriptionChanged(description = description))

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
    ProductScreenContent(
        state = ProductScreenState(
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