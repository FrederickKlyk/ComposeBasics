package de.fred.composedemo1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.ui.DetailScreen
import de.fred.composedemo1.ui.DetailScreenViewModel
import de.fred.composedemo1.ui.HomeScreenContent
import de.fred.composedemo1.ui.MainViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel

fun NavGraphBuilder.addMainGraph() {
    navigation(Navigator.NavTarget.Home.label, Navigator.NavTarget.RootModule.label) {

        composable(Navigator.NavTarget.Home.label) {
            val viewModel: MainViewModel = getViewModel()
            //val viewModel: MainViewModel = hiltViewModel()
            HomeScreenContent(viewModel)
        }
        composable(Navigator.NavTarget.Detail.label) {
            val viewModel: DetailScreenViewModel by viewModel()
            //val viewModel: DetailScreenViewModel = hiltViewModel()
            DetailScreen(viewModel)
        }
    }
}