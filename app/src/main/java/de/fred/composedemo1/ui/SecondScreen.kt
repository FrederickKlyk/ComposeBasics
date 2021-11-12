package de.fred.composedemo1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.fred.composedemo1.ui.utils.Navigator
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel


@Composable
fun SecondScreen() {
    val navigator: Navigator = get()
    val viewModel = getViewModel<SecondScreenViewModel>()
    Column() {
        Text(text = viewModel.getDetailText())
        Button(onClick = { navigator.navigateTo(Navigator.NavTarget.SecondFeature) }){
            Text(text = "zum zweiten Feature Module")
        }
    }
}

@Preview
@Composable
fun SecondScreenPreview() {
   // SecondScreen()
}