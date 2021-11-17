package de.fred.composedemo1.thirdfeature.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator

class ThirdFeatureViewModel(private val navigator: Navigator) : ViewModel(){

    init {
        Log.d("ThirdFeatureViewModel", "ThirdFeatureViewModel wurde initialisiert...")

    }

    fun navigateToSecondFeatureModule(){
        navigator.navigateTo(Navigator.NavTarget.SecondModule)
    }

    override fun onCleared() {
        Log.d("ThirdFeatureViewModel", "ThirdFeatureViewModel wurde abgeräumt...")
        super.onCleared()
    }
}