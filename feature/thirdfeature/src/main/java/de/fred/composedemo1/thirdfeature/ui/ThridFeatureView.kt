package de.fred.composedemo1.thirdfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.get

@Composable
fun ThirdFeatureView() {
    val viewModel: ThirdFeatureViewModel = get()
    ThirdFeatureContent(viewModel::navigateToSecondFeatureModule)
}

@Composable
fun ThirdFeatureContent(navigateToSecondFeatureModule: () -> Unit) {
    Column() {
        Text("Hallo, dies ist das ThirdFeature Module")
        Text("Das dritte Modul hat keine Abhängikeit zum zweiten Modul, navigation somit über Navigation Middlelayer möglich..")
        Button(onClick = navigateToSecondFeatureModule) {
            Text("zurück zum zweiten Modul")
        }
    }
}

@Preview
@Composable
fun ThirdFeatureContentPreview() {
    ThirdFeatureContent {}
}