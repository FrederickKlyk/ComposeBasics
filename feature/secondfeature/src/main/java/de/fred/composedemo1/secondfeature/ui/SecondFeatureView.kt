package de.fred.composedemo1.secondfeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fred.composedemo1.navigation.Navigator.NavTarget

@Composable
fun SecondFeatureView() {
    Column() {
        Text("Hallo, dies ist das secondFeature Module")
        Button(onClick = { /*TODO*/ }) {
            Text("zurück zu den ersten Views")
        }
    }
}