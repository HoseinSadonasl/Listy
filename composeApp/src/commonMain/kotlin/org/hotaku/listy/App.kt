package org.hotaku.listy

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.hotaku.listy.product_detail.ProductDetailScreenRoute
import org.hotaku.listy.product_detail.ProductDetailScreen
import org.hotaku.listy.products_list.presentation.ProductsScreen
import org.hotaku.listy.products_list.presentation.ProductsScreenRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = ProductsScreenRoute
        ) {
            composable<ProductsScreenRoute> {
                ProductsScreen(
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