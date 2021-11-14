package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.get

@Composable
fun SecondFeatureView() {
    val viewModel: SecondFeatureViewModel = get()
    Column() {
        Text("Hallo, dies ist das secondFeature Module")
        Button(onClick = viewModel::navigateToThirdFeatureModule) {
            Text("weiter zum dritten FeatureModule")
        }
    }
}