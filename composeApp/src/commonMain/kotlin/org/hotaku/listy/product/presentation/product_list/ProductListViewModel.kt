package org.hotaku.listy.product.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.hotaku.listy.category.domain.usecases.DeleteCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.hotaku.listy.category.presentation.asUiCategory
import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.domain.usecases.GetProductsUseCase
import org.hotaku.listy.product.domain.usecases.UpsertProductUseCase
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.asProduct
import org.hotaku.listy.product.presentation.asUiProduct

class ProductListViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val upsertProductUseCase: UpsertProductUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<ProductListScreenState>(ProductListScreenState())
    val state = _state
        .onStart {
            _state.update { it.copy(isLoading = true) }
            updateList()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = _state.value
        )

    private val _event = Channel<ProductListScreenEvent>()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: ProductListScreenIntent) {
        when (intent) {
            is ProductListScreenIntent.OnUpdateList -> updateList()
            ProductListScreenIntent.OnNewProduct -> initNewProduct()
            is ProductListScreenIntent.OnTabChange -> setSelectedTab(tabIndex = intent.tabIndex)
            is ProductListScreenIntent.OnDoneClick -> setProductDone(product = intent.product)
            is ProductListScreenIntent.OnProductItemClick -> onOpenProduct(productId = intent.productId)
        }
    }

    private fun onOpenProduct(productId: Int) {
        viewModelScope.launch {
            _event.send(ProductListScreenEvent.NavigateToProductScreen(productId = productId))
        }
    }

    private fun setProductDone(product: UiProduct) {
        if(product.done) return
        upsertProduct(product = product.asProduct().copy(done = true))
    }

    private fun upsertProduct(product: Product) {
        viewModelScope.launch {
            upsertProductUseCase(product = product)
        }
    }

    private fun setSelectedTab(tabIndex: Int) {
        _state.update {
            it.copy(
                selectedCategory = tabIndex
            )
        }
    }

    private fun initNewProduct() {
        viewModelScope.launch {
            _event.send(ProductListScreenEvent.NavigateToProductScreen())
        }
    }

    fun updateList() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .combine(getProductsUseCase(
                    categoryId = _state.value.selectedCategory.takeIf { it > 0  })
                ) { categories, products ->
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