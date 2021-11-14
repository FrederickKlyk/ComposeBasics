package de.fred.composedemo1.thirdfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import de.fred.composedemo1.thirdfeature.navigation.ThirdFeatureExternalNavigation
import org.koin.androidx.compose.get

@Composable
fun ThirdFeatureView() {
    Column() {
        val externalNavigation = get<ThirdFeatureExternalNavigation>()
        Text("Hallo, dies ist das ThirdFeature Module")
        Text("Das dritte Modul hat keine Abhängikeit zum zweiten Modul, navigation somit über externalNavigation Interface.")
        Button(onClick = { externalNavigation.navigateToSecondFeatureView() }) {
            Text("zurück zum zweiten Modul")
        }
    }
}