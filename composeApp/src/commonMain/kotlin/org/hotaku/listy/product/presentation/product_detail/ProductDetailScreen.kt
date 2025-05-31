package org.hotaku.listy.product.presentation.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.Clock
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.add_listy
import listy.composeapp.generated.resources.product_screen_add_category_dialog_title
import listy.composeapp.generated.resources.product_screen_category_dialog_category_name_placeholder
import listy.composeapp.generated.resources.product_screen_delete_dialog_confirm_delete
import listy.composeapp.generated.resources.product_screen_delete_dialog_message
import listy.composeapp.generated.resources.product_screen_delete_dialog_title
import listy.composeapp.generated.resources.product_screen_rename_category_dialog_title
import listy.composeapp.generated.resources.product_screen_the_item_is_sold
import org.hotaku.listy.core.presentation.composables.AlertDialog
import org.hotaku.listy.core.presentation.composables.InputDialog
import org.hotaku.listy.core.presentation.composables.TitleText
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_16dp
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.productImportance
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.NavigateBack
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.ShowSnackbar
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnCategoryNameChange
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDeleteClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDoneClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnEditCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnHideDeleteDialog
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnHideEditCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnImportanceChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnImportanceClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnImportanceDismissRequest
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnNewCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailDescriptionChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailNameChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSetProductCategory
import org.hotaku.listy.product.presentation.product_detail.composables.CategoryList
import org.hotaku.listy.product.presentation.product_detail.composables.EditProduct
import org.hotaku.listy.product.presentation.product_detail.composables.ProductDetailScreenScaffold
import org.hotaku.listy.product.presentation.product_detail.composables.categories
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is NavigateBack -> onBackClick()
                is ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = getString(event.messageRes))
                }
            }
        }
    }

    ProductDetailScreenContent(
        modifier = modifier,
        state = state,
        snackbarHostState = snackbarHostState,
        onBackClick = onBackClick,
        onIntent = viewModel::onIntent,
    )
}

@Composable
fun ProductDetailScreenContent(
    modifier: Modifier = Modifier,
    state: ProductDetailScreenState,
    snackbarHostState: SnackbarHostState,
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

        state.category != null -> {
            InputDialog(
                title = state.category.id?.let {
                    stringResource(Res.string.product_screen_rename_category_dialog_title)
                } ?: stringResource(Res.string.product_screen_add_category_dialog_title),
                value = state.category.name,
                placeholder = stringResource(Res.string.product_screen_category_dialog_category_name_placeholder),
                onValueChange = { onIntent(OnCategoryNameChange(categoryName = it)) },
                onConfirm = { onIntent(OnSaveCategory) },
                onDismissRequest = { onIntent(OnHideEditCategory) }
            )
        }
    }

    ProductDetailScreenScaffold(
        modifier = modifier,
        title = state.product?.name,
        onNavigateBack = onBackClick,
        snackbarHostState = snackbarHostState,
        onDeleteClick = { onIntent(OnDeleteClick) }.takeIf { state.product != null },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .aspectRatio(1f),
                    imageVector = vectorResource(Res.drawable.add_listy),
                    contentDescription = null,
                )
            }

            if (state.product?.done == true) {
                TitleText(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    text = stringResource(Res.string.product_screen_the_item_is_sold),
                    align = TextAlign.Center,
                    maxLine = 2
                )
            }

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
                productImportance = state.product?.importance ?: productImportance.first(),
                onDoneChange = { onIntent(OnDoneClick) },
                onImportanceChanged = {
                    onIntent(OnImportanceChanged(importance = it))
                },
                onImportanceClick = {
                    onIntent(OnImportanceClick)
                },
                onImportanceDismissRequest = {
                    onIntent(OnImportanceDismissRequest)
                },
                importanceExpended = state.importanceExpended,
            )

            VerticalSpacer_16dp()

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
                done = true,
                categoryId = 2,
                importance = productImportance.first(),
                dateCreated = Clock.System.now(),
            ),
            categories = categories,
            category = categories[1],
            isLoading = false,
            importanceExpended = true,
        ),
        snackbarHostState = remember { SnackbarHostState() },
        onBackClick = {},
        onIntent = {},
    )
}