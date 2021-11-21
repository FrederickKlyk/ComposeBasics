package de.fred.composedemo1.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import de.fred.composedemo1.navigation.Navigator
import de.fred.designsystem.buttons.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val navigator: Navigator) : BaseViewModel<DetailScreenViewModel>() {

    fun getDetailText(): String {
        return "Details12345"
    }

    fun navigateToSecondFeature() {
        navigator.navigateTo(Navigator.NavTarget.SecondModule)
    }
}