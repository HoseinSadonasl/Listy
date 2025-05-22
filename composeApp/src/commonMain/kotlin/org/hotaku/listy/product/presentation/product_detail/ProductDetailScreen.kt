package org.hotaku.listy.product.presentation.product_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.Clock
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_screen_delete_dialog_confirm_delete
import listy.composeapp.generated.resources.product_screen_delete_dialog_message
import listy.composeapp.generated.resources.product_screen_delete_dialog_title
import org.hotaku.listy.core.presentation.composables.AlertDialog
import org.hotaku.listy.core.presentation.composables.TopRoundedCard
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_16dp
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_24dp
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.NavigateBack
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnCategoryNameChange
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDeleteClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDoneClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnEditCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnHideDeleteDialog
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnNewCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailDescriptionChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailNameChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSetProductCategory
import org.hotaku.listy.product.presentation.product_detail.composables.CategoryList
import org.hotaku.listy.product.presentation.product_detail.composables.EditCategory
import org.hotaku.listy.product.presentation.product_detail.composables.EditProduct
import org.hotaku.listy.product.presentation.product_detail.composables.ProductDetailScreenScaffold
import org.hotaku.listy.product.presentation.product_detail.composables.categories
import org.jetbrains.compose.resources.stringResource
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

    when {
        state.showDeleteDialog -> {
            AlertDialog(
                title = stringResource(Res.string.product_screen_delete_dialog_title),
                message = stringResource(Res.string.product_screen_delete_dialog_message),
                confirmButtonText = stringResource(Res.string.product_screen_delete_dialog_confirm_delete),
                onDismissRequest = { onIntent(OnHideDeleteDialog) },
                onConfirm = { onIntent(ProductDetailScreenIntent.OnDeleteProduct) },
            )
        }
    }

    ProductDetailScreenScaffold(
        title = state.product?.name,
        onNavigateBack = onBackClick,
        onDeleteClick = { onIntent(OnDeleteClick) }.takeIf { state.product != null },
        content = {
            TopRoundedCard {
                VerticalSpacer_24dp()

                CategoryList(
                    categories = state.categories,
                    selectedCategory = state.product?.categoryId,
                    onCategorySelected = { categoryId ->
                        onIntent(OnSetProductCategory(id = categoryId))
                    },
                    onAddNewCategory = { onIntent(OnNewCategory) },
                    onEditCategoryClick = { categoryId ->
                        onIntent(OnEditCategory(id = categoryId))
                    }
                )

                VerticalSpacer_16dp()

                AnimatedVisibility(
                    visible = state.category != null
                ) {
                    EditCategory(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 0.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                        category = state.category,
                        onCategoryNameChange = { name ->
                            onIntent(OnCategoryNameChange(categoryName = name))
                        },
                        onSaveClick = { onIntent(OnSaveCategory) }
                    )

                }

                EditProduct(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onTitleChange = { name ->
                        onIntent(OnProductDetailNameChanged(name = name))
                    },
                    onDescriptionChange = { description ->
                        onIntent(OnProductDetailDescriptionChanged(description = description))

                    },
                    onSaveProduct = { onIntent(OnSaveClick) },
                    product = state.product,
                    onDoneChange = { onIntent(OnDoneClick) }
                )

                VerticalSpacer_16dp()

            }

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
            category = categories[1],
            isLoading = false,
        ),
        onBackClick = {},
        onIntent = {},
    )
}