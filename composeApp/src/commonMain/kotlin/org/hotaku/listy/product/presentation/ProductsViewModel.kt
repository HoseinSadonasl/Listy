package org.hotaku.listy.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.hotaku.listy.core.presentation.UiState
import org.hotaku.listy.core.presentation.UiText
import org.hotaku.listy.product.presentation.ProductsScreenIntents.*
import org.koin.android.annotation.KoinViewModel

class ProductsViewModel(

) : ViewModel() {
    var state = MutableStateFlow<UiState<ProductScreenState, UiText>>(UiState.Idle)
        .onStart { updateList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )
        private set

    fun onIntent(intent: ProductsScreenIntents) {
        when(intent) {
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

    private fun updateList() {

    }
}