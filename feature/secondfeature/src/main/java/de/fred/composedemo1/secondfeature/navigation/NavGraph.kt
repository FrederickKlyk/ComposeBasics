package de.fred.composedemo1.secondfeature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.secondfeature.ui.SecondFeatureView

fun NavGraphBuilder.addSecondFeatureGraph() {
    navigation(Navigator.NavTarget.SecondFeature.label, "second") {
        composable(Navigator.NavTarget.SecondFeature.label) {
            SecondFeatureView()
        }
    }
}