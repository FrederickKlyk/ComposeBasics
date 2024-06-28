package de.fred.composedemo1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.ui.DetailScreen
import de.fred.composedemo1.ui.DetailScreenViewModel
import de.fred.composedemo1.ui.HomeScreenContent
import de.fred.composedemo1.ui.MainViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.addMainGraph(popBackStack: () -> Unit) {
    navigation(startDestination = NavTarget.Home.label, route = NavTarget.RootModule.label) {
        composable(NavTarget.Home.label) {
            val viewModelKoin: MainViewModel = getViewModel()
            // val viewModelHilt: MainViewModel = hiltViewModel()
            HomeScreenContent(viewModelKoin)
        }
        composable(NavTarget.Detail.label) {
            val viewModelKoin: DetailScreenViewModel = getViewModel()
            // val viewModelHilt: DetailScreenViewModel = hiltViewModel()
            DetailScreen(viewModelKoin)
        }
    }
}
