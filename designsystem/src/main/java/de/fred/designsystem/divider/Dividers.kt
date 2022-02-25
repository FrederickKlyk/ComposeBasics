package de.fred.designsystem.divider

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object Dividers {

    @Composable
    fun Divider1DPGray400(modifier: Modifier) {
        Divider(color = Color(242, 242, 242), thickness = 1.dp, modifier = modifier)
    }
}