package de.fred.designsystem.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object CartItem {

    @Composable
    fun ShoppingCartItemBackground(content: @Composable BoxWithConstraintsScope.() -> Unit) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(end = 25.dp, bottom = 22.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color(241, 240, 247))
        ) {
            content.invoke(this)
        }
    }
}