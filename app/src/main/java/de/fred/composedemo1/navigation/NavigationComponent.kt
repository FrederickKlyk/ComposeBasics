package de.fred.composedemo1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.fred.composedemo1.secondfeature.navigation.addSecondFeatureGraph
import de.fred.composedemo1.shoppingcart.navigation.addShoppingCartGraph
import de.fred.composedemo1.thirdfeature.navigation.addThirdFeatureGraph
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator,
) {
    /**
     * With LaunchedEffect we create a CoroutineScope that is started as soon as our composable component is createdtestSTATE
     * and canceled as soon as the composition is removed.
     * As a result, whenever Navigator.navigateTo() is called, this snippet listens to it and performs the actual transition.
     */
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it.label) {
                popUpTo(it.label) // wichtig, damit der backstack aus unique nav entries besteht (auf Parameter achten)
            }
        }.launchIn(this)
    }

    // Navigation Directions
    NavHost(
        navController = navController,
        startDestination = NavTarget.RootModule.label
    ) {
        addMainGraph() { navController.popBackStack() }
        addSecondFeatureGraph() { navController.popBackStack() }
        addThirdFeatureGraph() { navController.popBackStack() }
        addShoppingCartGraph { navController.popBackStack() }
    }
}