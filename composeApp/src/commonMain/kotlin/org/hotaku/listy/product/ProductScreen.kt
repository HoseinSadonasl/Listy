package org.hotaku.listy.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_8dp
import org.hotaku.listy.product.ProductScreenIntents.OnDeleteClick
import org.hotaku.listy.product.ProductScreenIntents.OnDoneClick
import org.hotaku.listy.product.ProductScreenIntents.OnNewCategory
import org.hotaku.listy.product.ProductScreenIntents.OnProductDescriptionChanged
import org.hotaku.listy.product.ProductScreenIntents.OnProductNameChanged
import org.hotaku.listy.product.ProductScreenIntents.OnSaveClick
import org.hotaku.listy.product.composables.CategoryList
import org.hotaku.listy.product.composables.EditProduct
import org.hotaku.listy.product.composables.ProductScreenScaffold

@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
            CategoryList(
                categories = state.categories,
                selectedCategory = state.product?.categoryId,
                onCategorySelected = { categoryId ->

                },
                onAddNewCategory = { onIntent(OnNewCategory) }
            )

            VerticalSpacer_8dp()

            EditProduct(
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