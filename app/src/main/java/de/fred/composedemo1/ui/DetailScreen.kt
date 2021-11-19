package de.fred.composedemo1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun DetailScreen(viewModel: DetailScreenViewModel) {
    DetailScreenContent(viewModel::getDetailText, viewModel::navigateToSecondFeature)
}

@Composable
fun DetailScreenContent(getDetailText: () -> String, navigateToDetailFeature: () -> Unit) {
    Column() {
        Text(text = getDetailText())
        Button(onClick = navigateToDetailFeature) {
            Text(text = "zum zweiten Feature Module")
        }
    }
}

@Preview
@Composable
fun DetailScreenContentPreview() {
    DetailScreenContent({ "test" }) { }
}