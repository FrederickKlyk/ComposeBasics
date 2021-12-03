package de.fred.designsystem.buttons

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DefaultTopBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    title: String = "",
    buttonIcon: ImageVector,
    color: Color = MaterialTheme.colors.primary,
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = color,
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(buttonIcon, "")
            }
        }
    )
}