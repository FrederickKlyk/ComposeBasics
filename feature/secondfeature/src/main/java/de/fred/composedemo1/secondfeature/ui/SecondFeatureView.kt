package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import de.fred.composedemo1.navigation.DrawerRoute
import de.fred.composedemo1.navigation.NavTarget
import de.fred.designsystem.buttons.DefaultDrawer
import de.fred.designsystem.buttons.DefaultTopBar
import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume


@Composable // stateful
fun FeatureSecondContent(viewModel: FeatureSecondViewModel, secondFeatureModuleID: String) {
    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState by viewModel.uiState

    val continuation by viewModel.continuation.observeAsState()
    val cancel by viewModel.cancel.observeAsState()

    FeatureSecondContent(
        secondFeatureModuleID = secondFeatureModuleID,
        uiStateFlow = uiStateFlow,
        uiState = uiState,
        incrementUiStateFlowInteger = viewModel::incrementUiStateFlowInteger,
        downloadDataFromRepository = viewModel::downloadDataFromRepository,
        navigateToThirdFeatureModule = viewModel::navigateToThirdFeatureModule,
        startCoroutineConti = viewModel::coroutineContinuation,
        continuation = continuation,
        cancel = cancel
    )
}

@Composable // stateless
fun FeatureSecondContent(
    uiStateFlow: Int,
    uiState: FeatureSecondUIState,
    incrementUiStateFlowInteger: () -> Unit,
    downloadDataFromRepository: () -> Unit
) {
    Column() {
        when (uiState) {
            is FeatureSecondUIState.Error -> {
                Text("Error caused by: ${uiState.message}")
            }
            FeatureSecondUIState.Initial -> {
                Text("Initial state")
            }
            FeatureSecondUIState.Loaded -> {
                Text("Loaded successfully")
            }
            is FeatureSecondUIState.Loading -> {
                Text("Actual Progress: ${uiState.progress}")
            }
        }
        Text("The actual Integer of the stateFlow is: $uiStateFlow")
        Button(onClick = incrementUiStateFlowInteger) {
            Text("Incremnt number")
        }
        Button(onClick = downloadDataFromRepository, enabled = uiState !is FeatureSecondUIState.Loading) {
            Text("Download some data from the repository")
        }
    }
}

@Preview
@Composable
private fun FeatureSecondContentPreview2() {
    FeatureSecondContent(1, FeatureSecondUIState.Initial, {}, {})
}

@Composable // stateless
fun FeatureSecondContent(
    secondFeatureModuleID: String,
    uiStateFlow: Int,
    uiState: FeatureSecondUIState,
    incrementUiStateFlowInteger: () -> Unit,
    downloadDataFromRepository: () -> Unit,
    navigateToThirdFeatureModule: () -> Unit,
    startCoroutineConti: () -> Unit,
    continuation: Continuation<Boolean>?,
    cancel: CancellableContinuation<Boolean>?,
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
                is FeatureSecondUIState.Error -> {
                    Text("Fehler wegen: ${uiState.message}")
                }
                FeatureSecondUIState.Initial -> {
                    Text("Initialier Zustand")
                }
                FeatureSecondUIState.Loaded -> {
                    Text("Fertig geladen")
                }
                is FeatureSecondUIState.Loading -> {
                    Text("Progress: ${uiState.progress}")
                }
            }
            Text("Hallo, dies ist das secondFeature Module mit dem Übergabeparamter $secondFeatureModuleID, die Intnumber von stateFlow: $uiStateFlow")
            Button(onClick = incrementUiStateFlowInteger) {
                Text("Erhöhe die Zahl")
            }
            Button(onClick = downloadDataFromRepository, enabled = uiState !is FeatureSecondUIState.Loading) {
                Text("Lade etwas runter")
            }
            Button(onClick = navigateToThirdFeatureModule) {
                Text("weiter zum dritten FeatureModule")
            }
            Button(onClick = startCoroutineConti) {
                Text("start Coroutine Conti")
            }
            Button(onClick = { continuation?.resume(true) }) {
                Text("Continuation resumen mit true, state: ${continuation?.context}")
            }
            Button(onClick = { continuation?.resume(false) }) {
                Text("Continuation resumen mit false, state: ${continuation?.context}")
            }
            Button(onClick = { cancel?.resume(true) }) {
                Text("cancel mit true, state: ${cancel?.context}")
            }
            Button(onClick = { cancel?.resume(false) }) {
                Text("cancel mit false, state: ${cancel?.context}")
            }
            Button(onClick = { cancel?.cancel() }) {
                Text("cancel mit cancel, state: ${cancel?.context}")
            }
        }
    }
}

@Preview
@Composable
private fun FeatureSecondContentPreview() {
    FeatureSecondContent("", 1, FeatureSecondUIState.Initial, {}, {}, {}, {}, null, null)
}