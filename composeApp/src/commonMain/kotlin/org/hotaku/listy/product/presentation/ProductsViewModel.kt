package org.hotaku.listy.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.load_data_error
import org.hotaku.listy.category.domain.usecases.AddCategoryUseCase
import org.hotaku.listy.category.domain.usecases.DeleteCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.hotaku.listy.category.presentation.asUiCategory
import org.hotaku.listy.product.domain.usecases.AddProductUseCase
import org.hotaku.listy.product.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.product.domain.usecases.GetProductsUseCase
import org.hotaku.listy.product.presentation.ProductsScreenIntents.*
import org.jetbrains.compose.resources.stringResource

class ProductsViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<ProductScreenState>(ProductScreenState())
    val state = _state
        .onStart {
            updateList()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = _state.value
        )

    fun onIntent(intent: ProductsScreenIntents) {
        when (intent) {
            OnBottomSheetDismiss -> TODO()
            OnDeleteProduct -> TODO()
            is OnDoneClick -> TODO()
            is OnEmojiChange -> TODO()
            is OnOpenEditItemSheet -> TODO()
            is OnProductDescriptionChange -> TODO()
            is OnProductTitleChange -> TODO()
            OnSaveProduct -> TODO()
            is OnTabClick -> TODO()
        }
    }

    fun updateList() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getCategoriesUseCase()
                .combine(getProductsUseCase(categoryId = 0)) { categories, products ->
                    categories.map { it.asUiCategory() } to products.map { it.asUiProduct() }
                }
                .catch { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message.orEmpty(),
                            categories = emptyList(),
                            products = emptyList()
                        )
                    }
                }
                .collect { (categories, products) ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            categories = categories,
                            products = products
                        )
                    }
                }
        }
    }
}