package de.fred.composedemo1.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow = MutableSharedFlow<NavTarget>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavTarget) {
        _sharedFlow.tryEmit(navTarget)
    }

    // Screen Targets
    enum class NavTarget(val label: String) {
        RootModule("rootmodule"),
        SecondModule("secondmodule"),
        ThirdModule("thirdmodule"),
        Home("home"),
        Detail("detail"),
        SecondFeature("secondfeature"),
        ThirdFeature("thirdfeature")
    }
}