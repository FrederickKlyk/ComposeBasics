package de.fred.composedemo1.thirdfeature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.Navigator.NavTarget
import de.fred.composedemo1.thirdfeature.ui.ThirdFeatureView

fun NavGraphBuilder.addThirdFeatureGraph() {
    navigation(NavTarget.ThirdFeature.label, "third") {
        composable(NavTarget.ThirdFeature.label) {
            ThirdFeatureView()
        }
    }
}