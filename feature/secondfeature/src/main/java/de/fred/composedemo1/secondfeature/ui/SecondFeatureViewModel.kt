package de.fred.composedemo1.secondfeature.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.secondfeature.ui.SecondFeatureUIState.initial
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SecondFeatureViewModel(
   private val stateHandle: SavedStateHandle,
   private val navigator: Navigator,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(1)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    var uiState by mutableStateOf<SecondFeatureUIState>(initial)

    init {
        _uiStateFlow.value = stateHandle.get<Int>("test") ?: 0
        Log.d("state", "testSTATE: ${stateHandle.get("test") ?: 0}")
    }

    fun navigateToThirdFeatureModule() {
        navigator.navigateTo(Navigator.NavTarget.ThirdFeature)
    }

    fun incrementUiStateInteger() {
        _uiStateFlow.value += 1
        saveState()
    }

    fun downloadFakeData() {
        viewModelScope.launch() {
            fakeRepo()
                .onStart {
                    uiState = SecondFeatureUIState.loading(0)
                }.onCompletion {
                    uiState = SecondFeatureUIState.loaded
                }.catch {
                    uiState = SecondFeatureUIState.error("Fehler beim Laden")
                }.collect { progress ->
                    uiState = SecondFeatureUIState.loading(progress)
                }
        }
    }

    private suspend fun fakeRepo() = flow {
        listOf(10, 20, 30, 40, 50, 60, 70, 80, 90).forEach {
            delay(100)
            emit(it)
        }
    }

    private fun saveState() {
        stateHandle.set("test", uiStateFlow.value)
        Log.d("state", "testSTATE: ${stateHandle.get("test") ?: 0}")
    }
}