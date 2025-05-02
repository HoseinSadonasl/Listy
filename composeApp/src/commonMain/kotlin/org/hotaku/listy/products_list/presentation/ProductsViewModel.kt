package org.hotaku.listy.products_list.presentation

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
import kotlinx.datetime.Clock.System
import org.hotaku.listy.category.domain.usecases.AddCategoryUseCase
import org.hotaku.listy.category.domain.usecases.DeleteCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.hotaku.listy.category.presentation.asUiCategory
import org.hotaku.listy.products_list.domain.model.Product
import org.hotaku.listy.products_list.domain.usecases.AddProductUseCase
import org.hotaku.listy.products_list.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.products_list.domain.usecases.GetProductsUseCase
import org.hotaku.listy.products_list.presentation.ProductsScreenIntents.*

class ProductsViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
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
            is OnOpenEditItemSheet -> updateEditItemSheetState(isOpen = true, product = intent.product)
            OnBottomSheetDismiss -> updateEditItemSheetState(isOpen = false, product = null)
            OnNewProduct -> initNewProduct()
            is OnTabClick -> setSelectedTab(tabIndex = intent.tabIndex)
            is OnDoneClick -> setProductDone(product = intent.product)
            is OnEmojiChange -> setProductEmoji(emoji = intent.query)
            is OnProductTitleChange -> setProductName(name = intent.query)
            is OnProductDescriptionChange -> setProductDescription(description = intent.query)
            OnSaveProduct -> saveProduct()
            OnDeleteProduct -> deleteProduct()
            OnNewCategory -> showNewCategorySheet()
            is OnNewCategoryNameChange -> onCategoryNameChange(query = intent.query)
        }
    }

    private fun onCategoryNameChange(query: String) {

    }

    private fun showNewCategorySheet() {

    }

    private fun deleteProduct() {
        _state.value.product?.let { product ->
            viewModelScope.launch {
                deleteProductUseCase(product = product.asProduct())
                updateEditItemSheetState(isOpen = false, product = null)
            }
        }
    }

    private fun saveProduct() {
        _state.value.product?.let { product ->
            val newProduct = product.asProduct().copy(
                categoryId = _state.value.categoryId ?: 0,
                done = false
            )
            upsertProduct(product = newProduct)
            updateEditItemSheetState(isOpen = false, product = null)
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

    private fun setProductDone(product: UiProduct) {
        val doneProduct = product.asProduct().copy(done = true)
        upsertProduct(product = doneProduct)
    }

    private fun upsertProduct(product: Product) {
        viewModelScope.launch {
            addProductUseCase(product = product)
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
        _state.update {
            it.copy(
                isBottomSheetOpen = true,
                product = UiProduct(
                    name = "",
                    description = "",
                    categoryId = 1,
                    done = false,
                    dateCreated = System.now()
                )
            )
        }
    }

    private fun updateEditItemSheetState(isOpen: Boolean, product: UiProduct?) {
        _state.update {
            it.copy(
                isBottomSheetOpen = isOpen,
                product = product
            )
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