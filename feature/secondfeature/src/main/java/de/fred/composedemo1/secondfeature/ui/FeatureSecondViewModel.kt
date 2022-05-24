package de.fred.composedemo1.secondfeature.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import de.fred.composedemo1.navigation.NavTarget
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.secondfeature.ui.FeatureSecondUIState.Initial
import de.fred.designsystem.base.BaseViewModel
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine


class FeatureSecondViewModel(
    private val stateHandle: SavedStateHandle,
    private val navigator: Navigator,
) : BaseViewModel<FeatureSecondViewModel>() {

    private var _uiState = mutableStateOf<FeatureSecondUIState>(Initial)
    val uiState: State<FeatureSecondUIState>
        get() = _uiState

    private val _uiStateFlow = MutableStateFlow(1)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    var continuation = MutableLiveData<Continuation<Boolean>>()
    var cancel = MutableLiveData<CancellableContinuation<Boolean>>()

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

    fun coroutineContinuation() {
        var t1 = 1
        Log.d("scope", "before launch, t1: $t1") // 1
        viewModelScope.launch {
            Log.d("scope", "start launch, t1: $t1") // 1
            t1 = 2
            Log.d("scope", "start launch, t1: $t1") // 2
            val sc1 = suspendCoroutine<Boolean> { cont ->  //A continuation can be seen as the rest of the coroutine code after a suspension point
                Log.d("scope", "start suspendCoroutine, t1: $t1") //2
                t1 = 3
                Log.d("scope", "start suspendCoroutine, t1: $t1") //3
                continuation.value = cont  // Event wird geworfen, verlasse viewModelScope und führe Code nach viewModelScope durch
            }

            Log.d("scope",
                "Before suspendCancellableCoroutine") // " Wiedereintrittspunkt continuation.resume(true)" --> wird der Code innerhalb des viewModelScope Blocks nach dem suspendCoroutine ausgeführt

            val sc2 = suspendCancellableCoroutine<Boolean> { cancel ->
                cancel.invokeOnCancellation { //wird aufgerufen wenn der job gecancelt wird, oder in der ui cancel.cancel() aufgerufen wird.
                    Log.d("scope", "Aufräumen, wenn job oder suspendCancellableCoroutine Scope geschlossen wird.")
                }

                Log.d("scope", "start suspendCancellableCoroutine, t1: $t1, sc1: $sc1") // t1: 4, sc1: true
                t1 = 5
                Log.d("scope", "start suspendCancellableCoroutine, t1: $t1") // t1: 5
                this@FeatureSecondViewModel.cancel.value = cancel
            }

            Log.d("scope",
                "after suspendCoroutine, t1: $t1 and sc1: $sc1, and sc2: $sc2") //t1=5, sc1=true; sc2=false; nach "cancel.resume(false)" wird der Code innerhalb des viewModelScope Blocks nach dem suspendCancellableCoroutine Block ausgeführt
        }
        t1 = 4
        Log.d("scope", "after launch, t1: $t1") //4
    }


    fun downloadDataFromRepository() {
        viewModelScope.launch() {
            fakeRepo()
                .onStart {
                    _uiState.value = FeatureSecondUIState.Loading(0)
                }.onCompletion {
                    _uiState.value = FeatureSecondUIState.Loaded
                }.catch {
                    _uiState.value = FeatureSecondUIState.Error("Fehler beim Laden")
                }.collect { progress ->
                    _uiState.value = FeatureSecondUIState.Loading(progress)
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