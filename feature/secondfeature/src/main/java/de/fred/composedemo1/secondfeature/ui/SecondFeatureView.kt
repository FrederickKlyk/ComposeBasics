package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.navigation.Navigator.NavTarget
import org.koin.androidx.compose.get

@Composable
fun SecondFeatureView() {
    val navigator = get<Navigator>()
    Column() {
        Text("Hallo, dies ist das secondFeature Module")
        Button(onClick = { navigator.navigateTo(NavTarget.ThirdFeature) }) {
            Text("weiter zum dritten FeatureModule")
        }
    }
}