package org.hotaku.listy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.hotaku.listy.product.presentation.ProductsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ProductsScreen()
    }
}