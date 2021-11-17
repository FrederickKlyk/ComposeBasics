package de.fred.composedemo1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SecondScreen(viewModel: SecondScreenViewModel) {
    SecondScreenContent(viewModel::getDetailText, viewModel::navigateToSecondFeature)
}

@Composable
fun SecondScreenContent(getDetailText: () -> String, navigateToSecondFeature: () -> Unit) {
    Column() {
        Text(text = getDetailText())
        Button(onClick = navigateToSecondFeature) {
            Text(text = "zum zweiten Feature Module")
        }
    }
}

@Preview
@Composable
fun SecondScreenContentPreview() {
    SecondScreenContent({ "test" }) { }
}