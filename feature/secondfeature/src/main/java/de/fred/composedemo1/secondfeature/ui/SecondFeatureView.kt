package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SecondFeatureView() {
    val viewModel: SecondFeatureViewModel by viewModel{ parametersOf(SavedStateHandle())}
    val uiStateFlow = viewModel.uiStateFlow.collectAsState()
    val uiState = viewModel.uiState

    Column() {
        when(uiState){
            is SecondFeatureUIState.error -> { Text("Fehler wegen: ${uiState.message}")}
            SecondFeatureUIState.initial -> { Text("Initialier Zustand")}
            SecondFeatureUIState.loaded -> { Text("Fertig geladen")}
            is SecondFeatureUIState.loading -> { Text("Progress: ${uiState.progress}")}
        }
        Text("Hallo, dies ist das secondFeature Module, die Intnumber von stateFlow: ${uiStateFlow.value}")
        Button(onClick = { viewModel.incrementUiStateInteger() }) {
            Text("Erhöhe die Zahl")
        }
        Button(onClick = { viewModel.downloadFakeData() }) {
            Text("Lade etwas runter")
        }
        Button(onClick = viewModel::navigateToThirdFeatureModule) {
            Text("weiter zum dritten FeatureModule")
        }
    }
}