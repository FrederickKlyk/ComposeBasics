package de.fred.designsystem.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import de.fred.designsystem.colors.Colors
import de.fred.designsystem.dimensions.Dimensions

object Text {

    @Composable
    fun Text18spBoldBlack(text: String, modifier: Modifier = Modifier, textDecoration: TextDecoration = TextDecoration.None) {
        Text(
            text = text,
            modifier = modifier,
            fontSize = Dimensions.sp18,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textDecoration = textDecoration
        )
    }

    @Composable
    fun Text18spBoldGreen(text: String, modifier: Modifier = Modifier, textDecoration: TextDecoration = TextDecoration.None) {
        Text(
            text = text,
            modifier = modifier,
            fontSize = Dimensions.sp18,
            fontWeight = FontWeight.Bold,
            color = Colors.green100,
            textDecoration = textDecoration
        )
    }

    @Composable
    fun Text11spRegularBlack(text: String, modifier: Modifier = Modifier, textDecoration: TextDecoration = TextDecoration.None) {
        Text(text = text, fontSize = 11.sp, color = Color.Black, modifier = modifier, textDecoration = textDecoration)
    }

    @Composable
    fun Text16spBoldBlack(text: String){
        Text(
            text = text,
            fontSize = Dimensions.sp16,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = Dimensions.padding24)
        )
    }

    @Composable
    fun Text16spBoldBlackUnderlineEnd(text: String){
        Text(
            text = text,
            fontSize = Dimensions.sp16,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(end = Dimensions.padding28)
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}