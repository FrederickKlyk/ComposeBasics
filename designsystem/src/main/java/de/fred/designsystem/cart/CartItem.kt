package de.fred.designsystem.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import de.fred.designsystem.R
import de.fred.designsystem.colors.Colors.white100
import de.fred.designsystem.dimensions.Dimensions.padding24
import de.fred.designsystem.dimensions.Dimensions.size20
import de.fred.designsystem.dimensions.Dimensions.size72
import de.fred.designsystem.image.Image
import de.fred.designsystem.image.Image.CartArticleIcon
import de.fred.designsystem.text.Text

object CartItem {

    @Composable
    fun LazyItemScope.CartListItem(cartItemArticleData: CartItemArticleData, removeArticleItemFromCart: () -> Unit) {
        ShoppingCartItemBackground {
            CartArticleIcon(drawable = cartItemArticleData.articleIcon)
            Row(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(modifier = Modifier.padding(end = padding24)) {
                    Text.Text11spRegularBlack(text = cartItemArticleData.articleName)
                    Row(modifier = Modifier.align(Alignment.End)) {
                        Text.Text11spRegularBlack(text = stringResource(R.string.cart_quantity, cartItemArticleData.articleQuantity))
                        Text.Text11spRegularBlack(
                            text = stringResource(R.string.cart_price, cartItemArticleData.articlePrice),
                            textDecoration = TextDecoration.Underline,
                        )
                    }
                }
                Image.CartRemoveIcon(onClickAction = removeArticleItemFromCart::invoke)
            }
        }
    }

    @Composable
    fun ShoppingCartItemBackground(content: @Composable BoxWithConstraintsScope.() -> Unit) {
        BoxWithConstraints(
            modifier = Modifier
                .height(size72)
                .padding(bottom = padding24)
                .clip(shape = RoundedCornerShape(size20))
                .background(white100)
        ) {
            content.invoke(this)
        }
    }
}