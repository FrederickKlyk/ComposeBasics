package de.fred.composedemo1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fred.composedemo1.HomeScreen
import de.fred.composedemo1.Navigator
import de.fred.composedemo1.SecondScreen
import de.fred.composedemo1.SecondScreenViewModel
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
    }
}