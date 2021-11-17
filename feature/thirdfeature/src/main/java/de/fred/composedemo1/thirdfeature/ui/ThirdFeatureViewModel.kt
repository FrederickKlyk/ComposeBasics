package de.fred.composedemo1.thirdfeature.ui

import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator

class ThirdFeatureViewModel(private val navigator: Navigator) : ViewModel(){

    fun navigateToSecondFeatureModule(){
        navigator.navigateTo(Navigator.NavTarget.SecondModule)
    }
}