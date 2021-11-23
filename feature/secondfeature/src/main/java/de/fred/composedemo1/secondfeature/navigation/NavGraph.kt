package de.fred.composedemo1.secondfeature.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.ModuleRoutes
import de.fred.composedemo1.secondfeature.ui.SecondFeatureView
import de.fred.composedemo1.secondfeature.ui.SecondFeatureViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.addSecondFeatureGraph(popBackStack: () -> Unit) {
    navigation(startDestination = "${ModuleRoutes.SecondFeature.label}/{secondId}", route = "${ModuleRoutes.SecondModule.label}/{secondId}") {
        composable(
            route = "${ModuleRoutes.SecondFeature.label}/{secondId}",
            arguments = listOf(navArgument("secondId") { type = StringType })
        ) { backStackEntry ->
            val secondFeatureModuleID = backStackEntry.arguments?.getString("secondId")
            requireNotNull(secondFeatureModuleID) { "secondFeatureModuleID is null. Please make sure, seconId is set!" }
            val viewModel: SecondFeatureViewModel by viewModel { parametersOf(SavedStateHandle()) }

            SecondFeatureView(viewModel, secondFeatureModuleID)
        }
    }
}