package de.fred.composedemo1

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel


@Composable
fun SecondScreen() {
    val viewModel = getViewModel<SecondScreenViewModel>()
    Text(text = viewModel.getDetailText())
}

@Preview
@Composable
fun SecondScreenPreview() {
    SecondScreen()
}