package de.fred.composedemo1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.ui.DetailScreen
import de.fred.composedemo1.ui.DetailScreenViewModel
import de.fred.composedemo1.ui.HomeScreenContent
import de.fred.composedemo1.ui.MainViewModel
import org.koin.androidx.compose.get

fun NavGraphBuilder.addMainGraph() {
    navigation(Navigator.NavTarget.Home.label, Navigator.NavTarget.RootModule.label) {

        composable(Navigator.NavTarget.Home.label) {
            val viewModel = get<MainViewModel>()
            HomeScreenContent(viewModel)
        }
        composable(Navigator.NavTarget.Detail.label) {
            val viewModel = get<DetailScreenViewModel>()
            DetailScreen(viewModel)
        }
    }
}