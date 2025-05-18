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
            ProductListScreenIntent.OnNewProduct -> initNewProduct()
            is ProductListScreenIntent.OnTabClick -> setSelectedTab(tabIndex = intent.tabIndex)
            is ProductListScreenIntent.OnDoneClick -> setProductDone(productId = intent.productId)
            is ProductListScreenIntent.OnProductItemClick -> onOpenProduct(productId = intent.productId)
        }
    }

    private fun onOpenProduct(productId: Int) {
        viewModelScope.launch {
            _event.send(ProductListScreenEvent.NavigateToProductScreen(productId = productId))
        }
    }

    private fun setProductDone(productId: Int) {
        val product = findProductById(id = productId)
        product?.let {
            upsertProduct(product = it.copy(done = true))
        }
    }

    private fun findProductById(id: Int): Product? = _state.value.products.find {
        id == it.categoryId
    }?.asProduct()

    private fun upsertProduct(product: Product) {
        viewModelScope.launch {
            upsertProductUseCase(product = product)
        }
    }

    private fun setSelectedTab(tabIndex: Int) {
        _state.update {
            it.copy(
                selectedTabIndex = tabIndex
            )
        }
    }

    private fun initNewProduct() {
        viewModelScope.launch {
            _event.send(ProductListScreenEvent.NavigateToProductScreen())
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