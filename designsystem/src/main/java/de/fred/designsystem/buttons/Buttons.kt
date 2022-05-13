package de.fred.designsystem.buttons

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


object Buttons {
    @Composable
    fun DefaultButton(text: String, color: Color, onButtonClickCallback: () -> Unit) {
        Button(
            onClick = onButtonClickCallback,
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Text(text = text)
        }
    }

    @Composable
    fun ColumnScope.CTAButtonGreen(text: String, ctaButtonEnabled: Boolean, onButtonClickCallback: () -> Unit) {
        Button(
            onClick = onButtonClickCallback,
            enabled = ctaButtonEnabled,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(176, 213, 83)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 25.dp, end = 25.dp),
        ) {
            Text(text = text, color = Color.White)
        }
    }

    @Composable
    fun DefaultFAB(drawable: Int, color: Color = Color.Red, onButtonClickCallback: () -> Unit) {
        FloatingActionButton(
            onClick = onButtonClickCallback,
            backgroundColor = color,
            content =
            {
                Icon(
                    painter = painterResource(id = drawable),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        )
    }
}
