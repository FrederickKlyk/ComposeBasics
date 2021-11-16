package de.fred.composedemo1.secondfeature.ui

sealed class SecondFeatureUIState {
    object initial : SecondFeatureUIState()
    data class loading(val progress: Int) : SecondFeatureUIState()
    object loaded : SecondFeatureUIState()
    data class error(val message: String) : SecondFeatureUIState()
}