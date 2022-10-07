package de.fred.composedemo1.flows.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.fred.composedemo1.flows.ui.FlowsContent
import de.fred.composedemo1.flows.ui.FlowsViewModel
import de.fred.composedemo1.navigation.NavTarget
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.addFlowsFeatureGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavTarget.FlowsFeature.label,
        route = NavTarget.FlowsFeatureModule.label
    ) {
        composable(route = NavTarget.FlowsFeature.label) {
            val viewModel: FlowsViewModel = getViewModel()
            FlowsContent(viewModel, popBackStack)
        }
    }
}