package de.fred.designsystem.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.fred.composedemo1.navigation.DrawerRoute
import de.fred.composedemo1.navigation.NavTarget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DefaultDrawer(
    scope: CoroutineScope,
    drawerState: DrawerState,
    screens: List<DrawerRoute>,
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: NavTarget) -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            painter = painterResource(android.R.drawable.ic_dialog_info),
            contentDescription = "App icon"
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)

                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    }
}