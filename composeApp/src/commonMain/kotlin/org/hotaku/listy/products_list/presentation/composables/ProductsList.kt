package org.hotaku.listy.products_list.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hotaku.listy.products_list.presentation.UiProduct

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: List<UiProduct>,
    onProductItemClick: (UiProduct) -> Unit,
    onDoneClick: (UiProduct) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = products,
            key = { it.id!! }
        ) { product ->
            ProductItem(
                modifier = Modifier.clickable { onProductItemClick(product) },
                product = product,
                onDoneClick = { onDoneClick(product) }
            )
        }
    }
}