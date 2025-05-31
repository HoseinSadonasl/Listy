package org.hotaku.listy

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
    NavHost(
        navController = navController,
        startDestination = ProductListScreenRoute
    ) {
        composable<ProductListScreenRoute>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                )
            }
        ) {
            ProductListScreen(
                navigateToProductScreen = { id ->
                    navController.navigate(ProductDetailScreenRoute(productId = id))
                }
            )
        }
        composable<ProductDetailScreenRoute>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            }
        ) {
            ProductDetailScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}