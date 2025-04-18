package org.hotaku.listy.product.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hotaku.listy.product.presentation.UiProduct

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: List<UiProduct>,
    onProductItemClick: (UiProduct) -> Unit,
    onDoneClick: (UiProduct) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                modifier = Modifier.clickable { onProductItemClick(product) },
                product = product,
                onDoneClick = { onDoneClick(product) }
            )
        }
    }
}