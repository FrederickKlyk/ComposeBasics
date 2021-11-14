package de.fred.composedemo1.thirdfeature.ui

import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.thirdfeature.navigation.ThirdFeatureExternalNavigation

class ThirdFeatureViewModel(val thirdFeatureExternalNavigation: ThirdFeatureExternalNavigation, val navigator: Navigator) : ViewModel(){

    fun navigateToSecondFeatureModule(){
        navigator.navigateTo(Navigator.NavTarget.SecondFeature)
    }
}