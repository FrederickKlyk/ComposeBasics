package de.fred.composedemo1.secondfeature.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import de.fred.composedemo1.navigation.NavTarget
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.secondfeature.ui.FeatureSecondUIState.Initial
import de.fred.designsystem.buttons.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FeatureSecondViewModel(
   private val stateHandle: SavedStateHandle,
   private val navigator: Navigator,
) : BaseViewModel<FeatureSecondViewModel>() {

    private val _uiStateFlow = MutableStateFlow(1)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    var uiState by mutableStateOf<FeatureSecondUIState>(Initial)

    init {
        _uiStateFlow.value = stateHandle.get<Int>("test") ?: 0
        Log.d("state", "testSTATE: ${stateHandle.get("test") ?: 0}")
    }

    fun navigateToThirdFeatureModule() {
        navigator.navigateTo(NavTarget.ThirdModule)
    }

    fun incrementUiStateFlowInteger() {
        _uiStateFlow.value += 1
        saveState()
    }

    fun downloadDataFromRepository() {
        viewModelScope.launch() {
            fakeRepo()
                .onStart {
                    uiState = FeatureSecondUIState.Loading(0)
                }.onCompletion {
                    uiState = FeatureSecondUIState.Loaded
                }.catch {
                    uiState = FeatureSecondUIState.Error("Fehler beim Laden")
                }.collect { progress ->
                    uiState = FeatureSecondUIState.Loading(progress)
                }
        }
    }

    private suspend fun fakeRepo() = flow {
        listOf(10, 20, 30, 40, 50, 60, 70, 80, 90).forEach {
            delay(500)
            emit(it)
        }
    }

    private fun saveState() {
        stateHandle.set("test", uiStateFlow.value)
        Log.d("state", "testSTATE: ${stateHandle.get("test") ?: 0}")
    }
}