package de.fred.composedemo1.ui

import de.fred.composedemo1.navigation.Navigator
import de.fred.designsystem.buttons.base.BaseViewModel

class DetailScreenViewModel(private val navigator: Navigator) : BaseViewModel<DetailScreenViewModel>() {

    fun getDetailText(): String {
        return "Details12345"
    }

    fun navigateToSecondFeature() {
        navigator.navigateTo(Navigator.NavTarget.SecondModule)
    }
}