package org.hotaku.listy

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.hotaku.listy.product.ProductScreenRoute
import org.hotaku.listy.product.ProductScreen
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
                        navController.navigate(ProductScreenRoute(productId = id))
                    }
                )
            }
            composable<ProductScreenRoute>(
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally() }
            ) {
                ProductScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}