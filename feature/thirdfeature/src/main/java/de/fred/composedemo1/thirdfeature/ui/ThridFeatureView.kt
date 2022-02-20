package de.fred.composedemo1.thirdfeature.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThirdFeatureView(viewModel: ThirdFeatureViewModel, popBackStack: () -> Unit) {
    ThirdFeatureContent(viewModel::navigateToSecondFeatureModule, viewModel::navigateToShoppingCart, popBackStack)
}

@Composable
fun ThirdFeatureContent(navigateToSecondFeatureModule: () -> Unit, navigateToShoppingCart: () -> Unit, popBackStack: () -> Unit) {
    Column() {
        Icon(
            tint = Color.Black,
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    popBackStack()
                }
        )
        Text("Hallo, dies ist das ThirdFeature Module")
        Text("Das dritte Modul hat keine Abhängikeit zum zweiten Modul, navigation somit über Navigation Middlelayer möglich..")
        Button(onClick = navigateToSecondFeatureModule) {
            Text("zurück zum zweiten Modul")
        }
        Button(onClick = navigateToShoppingCart) {
            Text("Navigiere zum ShoppingCart")
        }
    }
}

@Preview
@Composable
fun ThirdFeatureContentPreview() {
    ThirdFeatureContent({}, {}, {})
}