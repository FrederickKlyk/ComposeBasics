package de.fred.composedemo1.secondfeature.ui

sealed class FeatureSecondUIState {
    object Initial : FeatureSecondUIState()
    data class Loading(val progress: Int) : FeatureSecondUIState()
    object Loaded : FeatureSecondUIState()
    data class Error(val message: String) : FeatureSecondUIState()
}