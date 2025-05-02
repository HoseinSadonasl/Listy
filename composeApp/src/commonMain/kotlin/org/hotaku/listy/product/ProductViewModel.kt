package org.hotaku.listy.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.hotaku.listy.category.domain.usecases.AddCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.category.presentation.asCategory
import org.hotaku.listy.category.presentation.asUiCategory
import org.hotaku.listy.product.ProductScreenEvents.NavigateBack
import org.hotaku.listy.product.ProductScreenIntent.*
import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.usecases.AddProductUseCase
import org.hotaku.listy.products_list.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.products_list.presentation.asProduct

class ProductViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ProductScreenState())
    val state = _state
        .onStart {
            getProductIfExist()
            getCategories()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = _state.value
        )

    private var _event = Channel<ProductScreenEvents>()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: ProductScreenIntent) {
        when (intent) {
            OnSaveClick -> saveProduct()
            OnBackClick -> sendEvent(event = NavigateBack)
            OnDeleteClick -> deleteProduct()
            OnDoneClick -> setProductDone()
            is OnProductDescriptionChanged -> setProductDescription(description = intent.description)
            is OnProductNameChanged -> setProductName(name = intent.name)
            OnNewCategory -> createNewCategory()
            OnEditCategory -> editCategory()
            OnSaveCategory -> upsertCategory()
            is OnCategoryNameChange -> setCategoryName(query = intent.categoryName)
        }
    }

    private fun sendEvent(event: ProductScreenEvents) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    private fun getProductIfExist() {

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

    private fun saveProduct() {
        _state.value.product?.let { product ->
            upsertProduct(product = product.asProduct())
        }
    }

    private fun setProductDescription(description: String) {
        _state.update {
            it.copy(
                product = it.product?.copy(description = description)
            )
        }
    }

    private fun setProductName(name: String) {
        _state.update {
            it.copy(
                product = it.product?.copy(name = name)
            )
        }
    }

    private fun setProductEmoji(emoji: String) {
        _state.update {
            it.copy(
                product = it.product?.copy(emoji = emoji)
            )
        }
    }

    private fun setProductDone() {
        _state.value.product?.let { product ->
            val doneProduct = product.asProduct().copy(done = true)
            upsertProduct(product = doneProduct)
        }
    }

    private fun upsertProduct(product: Product) {
        viewModelScope.launch {
            addProductUseCase(product = product)
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