package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hotaku.listy.product.presentation.UiProduct

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: List<UiProduct>,
    onProductItemClick: (Int) -> Unit,
    onDoneClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = products,
            key = { it.id!! }
        ) { product ->
            ProductItem(
                modifier = Modifier.clickable { onProductItemClick(product.id!!) },
                product = product,
                onDoneClick = { onDoneClick(product.id!!) }
            )
        }
    }
}