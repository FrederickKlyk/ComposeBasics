package de.fred.composedemo1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.ui.HomeScreen
import de.fred.composedemo1.ui.SecondScreen

fun NavGraphBuilder.addMainGraph(navigator: Navigator){
    navigation(Navigator.NavTarget.Home.label, "start") {
        composable(Navigator.NavTarget.Home.label) {
            HomeScreen(navigator)
        }
        composable(Navigator.NavTarget.Detail.label) {
            SecondScreen()
        }
    }
}