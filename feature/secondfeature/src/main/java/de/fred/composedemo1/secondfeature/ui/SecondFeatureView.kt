package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.compose.get
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SecondFeatureView() {
    val viewModel: SecondFeatureViewModel by viewModel { parametersOf(SavedStateHandle())}
    val uiState = viewModel.uiState.collectAsState()

    Column() {
        Text("Hallo, dies ist das secondFeature Module, die Intnumber ist: ${uiState.value}")
        Button(onClick = { viewModel.incrementUiStateInteger() }) {
            Text("Erhöhe die Zahl")
        }
        Button(onClick = viewModel::navigateToThirdFeatureModule) {
            Text("weiter zum dritten FeatureModule")
        }
    }
}