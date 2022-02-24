package de.fred.designsystem.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

object Text {

    @Composable
    fun Text11sp(text: String, modifier: Modifier = Modifier, textDecoration: TextDecoration = TextDecoration.None) {
        Text(text = text, fontSize = 11.sp, modifier = modifier, textDecoration = textDecoration)
    }
}