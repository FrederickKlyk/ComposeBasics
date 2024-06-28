package de.fred.composedemo1.flows.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun FlowsContent(viewModel: FlowsViewModel, popBackStack: () -> Unit) {
    FlowsContent(popBackStack)
}

@Composable
fun FlowsContent(popBackStack: () -> Unit) {
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
        Text("Hier kommen Flows rein")
    }
}

@Preview
@Composable
fun ThirdFeatureContentPreview() {
    FlowsContent({})
}