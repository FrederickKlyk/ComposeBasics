package de.fred.composedemo1.shoppingcart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.NavTarget
import de.fred.composedemo1.shoppingcart.ui.ShoppingCartContent
import de.fred.composedemo1.shoppingcart.ui.ShoppingCartViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.addShoppingCartGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavTarget.ShoppingCartFeature.label,
        route = NavTarget.ShoppingCartModule.label
    ) {
        composable(route = NavTarget.ShoppingCartFeature.label) {
            val viewModel: ShoppingCartViewModel = getViewModel()
            ShoppingCartContent(viewModel = viewModel)
        }
    }
}