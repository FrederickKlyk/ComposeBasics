package de.fred.composedemo1

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


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
    fun DefaultFAB(drawable: Int, color: Color, onButtonClickCallback: () -> Unit) {
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
