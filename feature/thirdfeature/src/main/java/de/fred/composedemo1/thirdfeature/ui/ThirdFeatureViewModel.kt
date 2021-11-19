package de.fred.composedemo1.thirdfeature.ui

import de.fred.composedemo1.navigation.Navigator
import de.fred.designsystem.buttons.base.BaseViewModel

class ThirdFeatureViewModel(private val navigator: Navigator) : BaseViewModel<ThirdFeatureViewModel>() {

    fun navigateToSecondFeatureModule() {
        navigator.navigateTo(Navigator.NavTarget.SecondModule)
    }
}