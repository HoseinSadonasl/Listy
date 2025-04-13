package org.hotaku.listy.product.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.hotaku.listy.core.presentation.UiState
import org.hotaku.listy.core.presentation.UiText
import org.hotaku.listy.product.presentation.ProductsScreenIntents.*
import org.hotaku.listy.product.presentation.composables.EditProduct
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreenContent(
    modifier: Modifier = Modifier,
    state: UiState<ProductScreenState, UiText>,
    onIntent: (ProductsScreenIntents) -> Unit
) {
    ProductsScreenScaffold(
        modifier = modifier,
        onAddClick = { onIntent(OnOpenEditItemSheet()) },
        content = {
            when (state) {
                is UiState.Error -> ErrorState(message = state.error)
                UiState.Idle -> { /* Do nothing */
                }

                UiState.Loading -> LoadingState()
                is UiState.Success -> {
                    if (state.data.isBottomSheetOpen) {
                        ModalBottomSheet(
                            onDismissRequest = { onIntent(OnBottomSheetDismiss) },
                            content = {
                                EditProduct(
                                    product = state.data.product,
                                    onEmojiChange = { emoji ->
                                        onIntent(
                                            OnEmojiChange(
                                                query = emoji
                                            )
                                        )
                                    },
                                    onTitleChange = { title ->
                                        onIntent(
                                            OnProductTitleChange(
                                                query = title
                                            )
                                        )
                                    },
                                    onDescriptionChange = { description ->
                                        onIntent(
                                            OnProductDescriptionChange(
                                                query = description
                                            )
                                        )
                                    },
                                    onSaveProduct = { onIntent(OnSaveProduct) },
                                    onDelete = { onIntent(OnDeleteProduct) }
                                )
                            }
                        )
                    }
                    ProductsCategoriesTabs(
                        tabs = state.data.categories,
                        selectedTabIndex = state.data.selectedTabIndex,
                        onTabClick = { onIntent(OnTabClick(tabIndex = it)) }
                    )
                    ProductsList(
                        products = state.data.products,
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
            }
        }
    )
}