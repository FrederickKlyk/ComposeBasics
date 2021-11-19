package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SecondFeatureView(viewModel: SecondFeatureViewModel) {
    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState = viewModel.uiState

    SecondFeatureContent(
        uiStateFlow = uiStateFlow,
        uiState = uiState,
        incrementUiStateInteger = viewModel::incrementUiStateInteger,
        downloadFakeData = viewModel::downloadFakeData,
        navigateToThirdFeatureModule = viewModel::navigateToThirdFeatureModule
    )
}

@Composable
fun SecondFeatureContent(
    uiStateFlow: Int,
    uiState: SecondFeatureUIState,
    incrementUiStateInteger: () -> Unit,
    downloadFakeData: () -> Unit,
    navigateToThirdFeatureModule: () -> Unit,
) {
    Column() {
        when (uiState) {
            is SecondFeatureUIState.error -> {
                Text("Fehler wegen: ${uiState.message}")
            }
            SecondFeatureUIState.initial -> {
                Text("Initialier Zustand")
            }
            SecondFeatureUIState.loaded -> {
                Text("Fertig geladen")
            }
            is SecondFeatureUIState.loading -> {
                Text("Progress: ${uiState.progress}")
            }
        }
        Text("Hallo, dies ist das secondFeature Module, die Intnumber von stateFlow: $uiStateFlow")
        Button(onClick = incrementUiStateInteger) {
            Text("Erhöhe die Zahl")
        }
        Button(onClick = downloadFakeData, enabled = uiState !is SecondFeatureUIState.loading) {
            Text("Lade etwas runter")
        }
        Button(onClick = navigateToThirdFeatureModule) {
            Text("weiter zum dritten FeatureModule")
        }
    }
}

@Preview
@Composable
private fun SecondFeatureContentPreview() {
    SecondFeatureContent(1, SecondFeatureUIState.initial, {}, {}, {})
}