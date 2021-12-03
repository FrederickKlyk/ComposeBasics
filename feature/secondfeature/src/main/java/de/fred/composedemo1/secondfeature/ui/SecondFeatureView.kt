package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import de.fred.composedemo1.navigation.DrawerRoute
import de.fred.composedemo1.navigation.NavTarget
import de.fred.designsystem.buttons.DefaultTopBar
import de.fred.designsystem.buttons.DefaultDrawer
import kotlinx.coroutines.launch

@Composable
fun SecondFeatureView(viewModel: SecondFeatureViewModel, secondFeatureModuleID: String) {
    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState = viewModel.uiState

    SecondFeatureContent(
        secondFeatureModuleID = secondFeatureModuleID,
        uiStateFlow = uiStateFlow,
        uiState = uiState,
        incrementUiStateInteger = viewModel::incrementUiStateInteger,
        downloadFakeData = viewModel::downloadFakeData,
        navigateToThirdFeatureModule = viewModel::navigateToThirdFeatureModule
    )
}

@Composable
fun SecondFeatureContent(
    secondFeatureModuleID: String,
    uiStateFlow: Int,
    uiState: SecondFeatureUIState,
    incrementUiStateInteger: () -> Unit,
    downloadFakeData: () -> Unit,
    navigateToThirdFeatureModule: () -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            DefaultDrawer(
                scope = scope,
                drawerState = drawerState,
                screens = listOf(DrawerRoute("Main", NavTarget.RootModule)),
                onDestinationClicked = { route ->

                }
            )
        },
    ) {
        Column() {
            DefaultTopBar(
                scope = scope,
                drawerState = drawerState,
                title = "Second Feature",
                buttonIcon = Icons.Filled.Menu,
            )
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
            Text("Hallo, dies ist das secondFeature Module mit dem Übergabeparamter $secondFeatureModuleID, die Intnumber von stateFlow: $uiStateFlow")
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
}

@Preview
@Composable
private fun SecondFeatureContentPreview() {
    SecondFeatureContent("test", 1, SecondFeatureUIState.initial, {}, {}, {})
}