package org.hotaku.listy.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.hotaku.listy.core.presentation.UiState
import org.hotaku.listy.core.presentation.UiText
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

    }

    private fun updateList() {

    }
}