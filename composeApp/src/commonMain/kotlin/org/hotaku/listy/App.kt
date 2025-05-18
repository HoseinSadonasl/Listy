package org.hotaku.listy

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreen
import org.hotaku.listy.product.presentation.product_detail.ProductDetailScreenRoute
import org.hotaku.listy.product.presentation.product_list.ProductListScreen
import org.hotaku.listy.product.presentation.product_list.ProductListScreenRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = ProductListScreenRoute
        ) {
            composable<ProductListScreenRoute> {
                ProductListScreen(
                    navigateToProductScreen = { id ->
                        navController.navigate(ProductDetailScreenRoute(productId = id))
                    }
                )
            }
            composable<ProductDetailScreenRoute>(
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally() }
            ) {
                ProductDetailScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}