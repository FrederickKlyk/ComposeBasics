package de.fred.composedemo1.secondfeature.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SecondFeatureViewModel(
    val stateHandle: SavedStateHandle,
    val navigator: Navigator,
) : ViewModel() {

    private val _uiState = MutableStateFlow(1)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = stateHandle.getLiveData("livedata", 0).value ?: 0
        Log.d("state","STATE: ${stateHandle.getLiveData("livedata", 0).value ?: 0}")
    }

    fun incrementUiStateInteger() {
        _uiState.value += 1
        saveState()
    }

    fun saveState() {
        stateHandle.set("livedata", uiState.value)
        Log.d("state","STATE: ${stateHandle.getLiveData("livedata", 0).value ?: 0}")
    }

    fun navigateToThirdFeatureModule() {
        navigator.navigateTo(Navigator.NavTarget.ThirdFeature)
    }
}