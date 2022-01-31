package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FeatureSecondContent(viewModel: FeatureSecondViewModel) {
    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState = viewModel.uiState

    FeatureSecondContent(
        uiStateFlow = uiStateFlow,
        uiState = uiState,
        incrementUiStateFlowInteger = viewModel::incrementUiStateFlowInteger,
        downloadDataFromRepository = viewModel::downloadDataFromRepository,
    )
}

@Composable
fun FeatureSecondContent(
    uiStateFlow: Int,
    uiState: FeatureSecondUIState,
    incrementUiStateFlowInteger: () -> Unit,
    downloadDataFromRepository: () -> Unit,
) {
    Column() {
        when (uiState) {
            is FeatureSecondUIState.Error -> {
                Text("Fehler wegen: ${uiState.message}.")
            }
            FeatureSecondUIState.Initial -> {
                Text("Initialier UI-Zustand mit einen von einem StateFlow zu " +
                        "einem State gecasteten Zustand: $uiStateFlow")
            }
            FeatureSecondUIState.Loaded -> {
                Text("Erfolgreich geladen.")
            }
            is FeatureSecondUIState.Loading -> {
                Text("Progress: ${uiState.progress}")
            }
        }
        Button(onClick = incrementUiStateFlowInteger) {
            Text("Erhöhe die Zahl")
        }
        Button(onClick = downloadDataFromRepository, enabled = uiState !is FeatureSecondUIState.Loading) {
            Text("Lade etwas runter")
        }
    }
}

@Preview
@Composable
private fun FeatureSecondContentPreview() {
    FeatureSecondContent(1, FeatureSecondUIState.Initial, {}, {})
}