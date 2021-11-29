package de.fred.designsystem.buttons

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DefaultTopBar(
    title: String = "",
    buttonIcon: ImageVector,
    color: Color = MaterialTheme.colors.primary,
    onButtonClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = color,
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(buttonIcon, "")
            }
        }
    )
}