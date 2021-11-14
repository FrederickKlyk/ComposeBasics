package de.fred.composedemo1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.ui.HomeScreen
import de.fred.composedemo1.ui.SecondScreen
import org.koin.java.KoinJavaComponent.inject

fun NavGraphBuilder.addMainGraph(){
    val navigator: Navigator by inject(Navigator::class.java)
    navigation(Navigator.NavTarget.Home.label, "start") {
        composable(Navigator.NavTarget.Home.label) {
            HomeScreen(navigator)
        }
        composable(Navigator.NavTarget.Detail.label) {
            SecondScreen()
        }
    }
}