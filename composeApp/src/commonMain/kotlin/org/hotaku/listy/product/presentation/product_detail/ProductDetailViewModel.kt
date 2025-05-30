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
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.product_screen_category_name_empty_errr_message
import org.hotaku.listy.category.domain.usecases.AddCategoryUseCase
import org.hotaku.listy.category.domain.usecases.GetCategoriesUseCase
import org.hotaku.listy.category.presentation.UiCategory
import org.hotaku.listy.category.presentation.asCategory
import org.hotaku.listy.category.presentation.asUiCategory
import org.hotaku.listy.product.domain.model.Product
import org.hotaku.listy.product.domain.usecases.DeleteProductUseCase
import org.hotaku.listy.product.domain.usecases.GetProductUseCase
import org.hotaku.listy.product.domain.usecases.UpsertProductUseCase
import org.hotaku.listy.product.presentation.Importance
import org.hotaku.listy.product.presentation.ImportanceEnum
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.asProduct
import org.hotaku.listy.product.presentation.asUiProduct
import org.hotaku.listy.product.presentation.productImportance
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.NavigateBack
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenEvent.ShowSnackbar
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnBackClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnCategoryNameChange
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDeleteClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDeleteProduct
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnDoneClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnEditCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnHideDeleteDialog
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnImportanceChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnImportanceClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnImportanceDismissRequest
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnNewCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailDescriptionChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnProductDetailNameChanged
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveCategory
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSaveClick
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenIntent.OnSetProductCategory

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
            OnDeleteClick -> showDeleteProductDialog()
            OnDeleteProduct -> deleteProduct()
            OnHideDeleteDialog -> showDeleteProductDialog(show = false)
            OnDoneClick -> setProductDone()
            is OnProductDetailDescriptionChanged -> setProductDescription(description = intent.description)
            is OnProductDetailNameChanged -> setProductName(name = intent.name)
            OnNewCategory -> createNewCategory()
            OnSaveCategory -> upsertCategory()
            is OnEditCategory -> editCategory(intent.id)
            is OnSetProductCategory -> setCategory(intent.id)
            is OnCategoryNameChange -> setCategoryName(query = intent.categoryName)
            is OnImportanceChanged -> setProductImportance(importance = intent.importance)
            OnImportanceClick-> showImportanceMenu()
            OnImportanceDismissRequest -> showImportanceMenu(show = false)
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
            importance = productImportance.first(),
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

    private fun showImportanceMenu(show: Boolean = true) {
        _state.update {
            it.copy(
                importanceExpended = show
            )
        }

    }

    private fun setProductImportance(importance: Importance) {
        createNewProductIfNotExist()
        _state.update {
            it.copy(
                product = it.product?.copy(importance = importance)
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

    private fun showDeleteProductDialog(show: Boolean = true) {
        _state.update { 
            it.copy(
                showDeleteDialog = show
            )
        }
    }
    
    private fun deleteProduct() {
        _state.value.product?.let { product ->
            viewModelScope.launch {
                deleteProductUseCase(product = product.asProduct())
                showDeleteProductDialog(show = false)
                sendEvent(NavigateBack)
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

    private fun setCategory(id: Int) {
        _state.value.product?.let {  product ->
            _state.update {
                it.copy(
                    product = product.copy(categoryId = id)
                )
            }
        }
    }

    private fun editCategory(id: Int) {
        _state.update {
            it.copy(
                category = it.categories.find { category ->
                    category.id == id
                }
            )
        }
    }

    private fun setCategoryName(query: String) {
        _state.update {
            it.copy(
                category = it.category!!.copy(
                    name = query
                )
            )
        }
    }

    private fun upsertCategory() {
        _state.value.category?.let { category ->
            if (category.name.isBlank()) {
                sendEvent(ShowSnackbar(messageRes = Res.string.product_screen_category_name_empty_errr_message))
                return
            }
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