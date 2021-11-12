package de.fred.composedemo1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fred.composedemo1.ui.HomeScreen
import de.fred.composedemo1.ui.utils.Navigator
import de.fred.composedemo1.ui.SecondScreen
import de.fred.composedemo1.secondfeature.ui.SecondFeatureView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator,
) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it.label)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = Navigator.NavTarget.Home.label
    ) {
        composable(Navigator.NavTarget.Home.label) {
            HomeScreen(navigator)
        }
        composable(Navigator.NavTarget.Detail.label) {
            SecondScreen()
        }
        composable(Navigator.NavTarget.SecondFeature.label){
            SecondFeatureView()
        }
    }
}