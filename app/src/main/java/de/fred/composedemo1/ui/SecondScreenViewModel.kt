package de.fred.composedemo1.ui

import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator

class SecondScreenViewModel(private val navigator: Navigator) : ViewModel() {

    fun getDetailText(): String {
        return "Details12345"
    }

    fun navigateToSecondFeature(){
        navigator.navigateTo(Navigator.NavTarget.SecondFeature)
    }
}