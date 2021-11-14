package de.fred.composedemo1.navigation

import de.fred.composedemo1.thirdfeature.navigation.ThirdFeatureExternalNavigation

class ThirdFeatureExternalNavigationImpl(
    private val navigator: Navigator
) : ThirdFeatureExternalNavigation {

    override fun navigateToSecondFeatureView() {
        navigator.navigateTo(Navigator.NavTarget.SecondFeature)
    }
}