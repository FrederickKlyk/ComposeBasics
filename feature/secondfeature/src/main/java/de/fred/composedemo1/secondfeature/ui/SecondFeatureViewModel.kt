package de.fred.composedemo1.secondfeature.ui

import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator

class SecondFeatureViewModel(val navigator: Navigator) : ViewModel() {

    fun navigateToThirdFeatureModule(){
        navigator.navigateTo(Navigator.NavTarget.ThirdFeature)
    }
}