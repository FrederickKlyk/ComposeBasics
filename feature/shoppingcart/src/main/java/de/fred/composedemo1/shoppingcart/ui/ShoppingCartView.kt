package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.fred.composedemo1.shoppingcart.R
import de.fred.designsystem.buttons.Buttons.CTAButtonGreen
import de.fred.designsystem.cart.CartItem.CartListItem
import de.fred.designsystem.cart.CartItemArticleData
import de.fred.designsystem.dimensions.Dimensions.padding20
import de.fred.designsystem.dimensions.Dimensions.padding24
import de.fred.designsystem.dimensions.Dimensions.padding32
import de.fred.designsystem.divider.Dividers.Divider1DPGray400
import de.fred.designsystem.image.Image.ImageAvatar
import de.fred.designsystem.text.Text.Text16spBoldBlack
import de.fred.designsystem.text.Text.Text16spBoldBlackUnderlineEnd
import de.fred.designsystem.text.Text.Text18spBoldBlack
import de.fred.designsystem.text.Text.Text18spBoldGreen
import java.math.BigDecimal

@Composable // stateful composable function
fun ShoppingCartContent(viewModel: ShoppingCartViewModel) {
    val shoppingCartItemList: List<ShoppingCartItemViewModel> = viewModel.shoppingCartItems
    val totalPrice by viewModel.shoppingCartTotalPriceState
    val isCtaButtonEnabled by viewModel.isCtaButtonEnabledState

    ShoppingCartContent(
        shoppingCartItemList = shoppingCartItemList,
        totalPrice = totalPrice,
        isCtaButtonEnabled = isCtaButtonEnabled,
        startCashOutProcess = viewModel::startCashOutProcess
    )
}

@Composable // stateless composable function
fun ShoppingCartContent(
    shoppingCartItemList: List<ShoppingCartItemViewModel>,
    totalPrice: BigDecimal,
    isCtaButtonEnabled: Boolean,
    startCashOutProcess: () -> Unit,
) {
    Column {
        ShoppingCartHeader()
        ShoppingCartList(shoppingCartItemList)
        ShoppingCartBottom(totalPrice, isCtaButtonEnabled, startCashOutProcess)
    }
}

@Composable
fun ShoppingCartHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = padding24, end = padding24)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // left side: label
        Column {
            Text18spBoldBlack(text = stringResource(R.string.shopping_cart_header_label_first), modifier = Modifier.padding(top = padding24))
            Text18spBoldGreen(text = stringResource(R.string.shopping_cart_header_label_last))
        }
        // right side: user icon
        ImageAvatar(drawable = R.drawable.avatar_with_bubble)
    }
}

@Composable
fun ShoppingCartList(shoppingCartItemList: List<ShoppingCartItemViewModel>) {
    LazyColumn(modifier = Modifier.padding(top = padding32, start = padding24, end = padding24)) {
        items(items = shoppingCartItemList) { shoppingCartItem ->
            CartListItem(
                cartItemArticleData = shoppingCartItem.cartItemArticleData,
                removeArticleItemFromCart = shoppingCartItem::removeArticleItemFromShoppingCart
            )
        }
    }
}

@Composable
fun ColumnScope.ShoppingCartBottom(totalPrice: BigDecimal, isCtaButtonEnabled: Boolean, startCashOutProcess: () -> Unit) {
    Divider1DPGray400(modifier = Modifier.padding(top = padding24, bottom = padding20))
    Row {
        Text16spBoldBlack(text = stringResource(R.string.shopping_cart_price_label))
        Text16spBoldBlackUnderlineEnd(text = stringResource(R.string.cart_price, totalPrice))
    }
    Divider1DPGray400(modifier = Modifier.padding(top = padding24, bottom = padding20))

    CTAButtonGreen(
        text = stringResource(R.string.shopping_cart_cta_label),
        isCtaButtonEnabled = isCtaButtonEnabled,
        onButtonClickCallback = startCashOutProcess
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartHeaderPreview() {
    ShoppingCartHeader()
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartListPreview() {
    ShoppingCartList(
        listOf(
            ShoppingCartItemViewModel(
                cartItemArticleData = CartItemArticleData(
                    articleId = 1,
                    articleIcon = R.drawable.article1,
                    articleName = "Kaktus",
                    articlePrice = BigDecimal(2),
                    articleQuantity = 1
                ),
                onShoppingCartStateEvent = remember { mutableStateOf(ShoppingCartStates.Initial) }
            ),
            ShoppingCartItemViewModel(
                cartItemArticleData = CartItemArticleData(
                    articleId = 2,
                    articleIcon = R.drawable.article2,
                    articleName = "Obst",
                    articlePrice = BigDecimal(3.50),
                    articleQuantity = 2
                ),
                onShoppingCartStateEvent = remember { mutableStateOf(ShoppingCartStates.Initial) }
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartBottomPreview() {
    Column {
        ShoppingCartBottom(BigDecimal.valueOf(11), true) { }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartContentPreview() {
    ShoppingCartContent(
        listOf(
            ShoppingCartItemViewModel(
                cartItemArticleData = CartItemArticleData(
                    articleId = 1,
                    articleIcon = R.drawable.article1,
                    articleName = "Kaktus",
                    articlePrice = BigDecimal(2),
                    articleQuantity = 1
                ),
                onShoppingCartStateEvent = remember { mutableStateOf(ShoppingCartStates.Initial) }
            )
        ),
        BigDecimal.valueOf(2.0),
        true
    ) {}
}