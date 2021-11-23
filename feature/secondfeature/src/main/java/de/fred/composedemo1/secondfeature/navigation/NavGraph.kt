package de.fred.composedemo1.secondfeature.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.ModuleRoutes
import de.fred.composedemo1.navigation.NavTarget
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.secondfeature.ui.SecondFeatureView
import de.fred.composedemo1.secondfeature.ui.SecondFeatureViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.addSecondFeatureGraph() {
    navigation(startDestination = NavTarget.SecondFeatureModule.SecondFeature.label, route = ModuleRoutes.SecondModule.label) {
        composable(route = NavTarget.SecondFeatureModule.SecondFeature.label) { backStackEntry ->
            val secondFeatureModuleID = backStackEntry.arguments?.getString("secondId")
            requireNotNull(secondFeatureModuleID) { "secondFeatureModuleID is null. Please make sure, seconId is set!" }
            val viewModel: SecondFeatureViewModel by viewModel { parametersOf(SavedStateHandle()) }
            SecondFeatureView(viewModel, secondFeatureModuleID)
        }
    }
}