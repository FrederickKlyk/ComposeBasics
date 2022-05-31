package de.fred.designsystem.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.fred.designsystem.R
import de.fred.designsystem.dimensions.Dimensions
import de.fred.designsystem.dimensions.Dimensions.padding16
import de.fred.designsystem.dimensions.Dimensions.size50

object Image {

    @Composable
    fun ImageAvatar(drawable: Int) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "",
            modifier = Modifier
                .size(size50)
                .padding(top = padding16)
        )
    }

    @Composable
    fun BoxWithConstraintsScope.CartArticleIcon(drawable: Int){
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "",
            Modifier
                .height(maxHeight)
                .width(Dimensions.size80)
        )
    }

    @Composable
    fun CartRemoveIcon(onClickAction: () -> Unit){
        Image(
            painter = painterResource(id = R.drawable.trash2),
            contentDescription = "",
            modifier = Modifier
                .padding(end = Dimensions.padding12)
                .clickable { onClickAction.invoke() }
                .size(width = Dimensions.padding24, height = Dimensions.padding24)
        )
    }

}