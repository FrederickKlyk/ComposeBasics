package de.fred.designsystem.base

import android.util.Log
import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.NavTarget

abstract class BaseViewModel<T : ViewModel> : ViewModel() {

    private val className: String? = this::class.simpleName

    init {
        Log.d("ViewModel", "$className wurde initialisiert...")
    }

    open fun navigateToSpecificView(navTarget: NavTarget){
        Log.d("navigation", "navigate to ${navTarget.label}")
    }

    override fun onCleared() {
        Log.d("ViewModel", "$className wurde abgeräumt...")
        super.onCleared()
    }
}