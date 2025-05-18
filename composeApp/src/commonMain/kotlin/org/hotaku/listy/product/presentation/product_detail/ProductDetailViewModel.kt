package org.hotaku.listy.product.presentation.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.hotaku.listy.category.domain.usecases.AddCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.category.presentation.asCategory
import org.hotaku.listy.category.presentation.asUiCategory
import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.*
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.*
import org.hotaku.listy.product.domain.usecases.UpsertProductUseCase
import org.hotaku.listy.product.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.product.domain.usecases.GetProductUseCase
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.asProduct
import org.hotaku.listy.product.presentation.asUiProduct

class ProductDetailViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val upsertProductUseCase: UpsertProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailScreenState())
    val state = _state
        .onStart {
            getProductIfExist()
            getCategories()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = _state.value
        )

    private var _event = Channel<ProductDetailScreenEvent>()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: ProductDetailScreenIntent) {
        when (intent) {
            OnSaveClick -> saveProduct()
            OnBackClick -> sendEvent(event = NavigateBack)
            OnDeleteClick -> deleteProduct()
            OnDoneClick -> setProductDone()
            is OnProductDetailDescriptionChanged -> setProductDescription(description = intent.description)
            is OnProductDetailNameChanged -> setProductName(name = intent.name)
            OnNewCategory -> createNewCategory()
            OnEditCategory -> editCategory()
            OnSaveCategory -> upsertCategory()
            is OnCategoryNameChange -> setCategoryName(query = intent.categoryName)
            is OnProductDetailDoneChanged -> {}
        }
    }

    private fun sendEvent(event: ProductDetailScreenEvent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    private fun getProductIfExist() {
        val productId = savedStateHandle.toRoute<ProductDetailScreenRoute>().productId
        productId?.let {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                val product = getProductUseCase(productId = it)
                _state.update {
                    it.copy(
                        isLoading = false,
                        product = product.asUiProduct()
                    )
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { categories ->
                _state.update {
                    it.copy(
                        categories = categories.map { it.asUiCategory() }
                    )
                }
            }
        }
    }

    private fun createNewProductIfNotExist() {
        if (_state.value.product != null) return
        val newProduct = UiProduct(
            name = "",
            description = "",
            categoryId = 0,
            dateCreated = Clock.System.now()
        )
        _state.update {
            it.copy(
                product = newProduct
            )
        }
    }

    private fun saveProduct() {
        _state.value.product?.let { product ->
            upsertProduct(product = product.asProduct())
            sendEvent(NavigateBack)
        }
    }

    private fun setProductDescription(description: String) {
        createNewProductIfNotExist()
        _state.update {
            it.copy(
                product = it.product?.copy(description = description)
            )
        }
    }

    private fun setProductName(name: String) {
        createNewProductIfNotExist()
        _state.update {
            it.copy(
                product = it.product?.copy(name = name)
            )
        }
    }

    private fun setProductDone() {
        _state.value.product?.let { product ->
            _state.update {
                it.copy(
                    product = product.copy(done = !product.done)
                )
            }
        }
    }

    private fun upsertProduct(product: Product) {
        viewModelScope.launch {
            upsertProductUseCase(product = product)
        }
    }

    private fun deleteProduct() {
        _state.value.product?.let { product ->
            viewModelScope.launch {
                deleteProductUseCase(product = product.asProduct())
            }
        }
    }

    private fun createNewCategory() {
        _state.update {
            it.copy(
                category = UiCategory(
                    name = ""
                )
            )
        }
    }

    private fun editCategory() {
        _state.update {
            it.copy(
                category = it.categories.find { category ->
                    category.id == it.product?.categoryId
                }
            )
        }
    }

    private fun setCategoryName(query: String) {
        _state.value.category?.let { category ->
            _state.update {
                it.copy(
                    category = category.copy(
                        name = query
                    )
                )
            }
        }
    }

    private fun upsertCategory() {
        _state.value.category?.let { category ->
            viewModelScope.launch() {
                addCategoryUseCase(category = category.asCategory())
                _state.update {
                    it.copy(
                        category = null
                    )
                }
            }
        }
    }

}