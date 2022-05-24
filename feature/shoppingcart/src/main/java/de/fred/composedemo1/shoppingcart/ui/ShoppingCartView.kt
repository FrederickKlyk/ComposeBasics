package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fred.composedemo1.shoppingcart.R
import de.fred.designsystem.buttons.Buttons.CTAButtonGreen
import de.fred.designsystem.cart.CartItem.ShoppingCartItemBackground
import de.fred.designsystem.divider.Dividers.Divider1DPGray400
import de.fred.designsystem.text.Text.Text11sp
import java.math.BigDecimal

@Composable // stateful composable function
fun ShoppingCartContent(viewModel: ShoppingCartViewModel) {
    val shoppingCartItemList: List<CartItemSuperViewModel> = viewModel.shoppingCartItems
    val totalPrice by viewModel.shoppingCartTotalPriceState
    val isCtaButtonEnabled by viewModel.isCtaButtonEnabledState

    ShoppingCartContent(
        shoppingCartItemList = shoppingCartItemList,
        totalPrice = totalPrice,
        isCtaButtonEnabled = isCtaButtonEnabled,
        startCashOutProcess = viewModel::startCashOutProcess
    )
}

@Composable
fun ShoppingCartContent(
    shoppingCartItemList: List<CartItemSuperViewModel>,
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
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.shopping_cart_header_label_first),
                modifier = Modifier.padding(top = 25.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.shopping_cart_header_label_last),
                color = Color(176, 213, 83),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Image(
            painter = painterResource(id = R.drawable.avatar_with_bubble),
            contentDescription = "",
            modifier = Modifier
                .size(51.dp)
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun ShoppingCartList(shoppingCartItemList: List<CartItemSuperViewModel>) {
    LazyColumn(modifier = Modifier.padding(top = 32.dp, start = 25.dp)) {
        items(items = shoppingCartItemList) { shoppingCartItem ->
            ShoppingCartItemBackground {
                Image(
                    painter = painterResource(id = shoppingCartItem.cartItemArticleData.articleIcon),
                    contentDescription = "",
                    Modifier
                        .height(maxHeight)
                        .width(80.dp)
                )
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(modifier = Modifier.padding(end = 24.dp)) {
                        Text11sp(text = shoppingCartItem.cartItemArticleData.articleName)
                        Row(modifier = Modifier.align(Alignment.End)) {
                            Text11sp(text = "${shoppingCartItem.cartItemArticleData.articleQuantity}x ")
                            Text11sp(
                                text = "${shoppingCartItem.cartItemArticleData.articlePrice}€",
                                textDecoration = TextDecoration.Underline,
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.trash2),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable {
                                shoppingCartItem.removeArticleItemFromShoppingCart()
                            }
                            .size(width = 24.dp, height = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnScope.ShoppingCartBottom(totalPrice: BigDecimal, isCtaButtonEnabled: Boolean, startCashOutProcess: () -> Unit) {
    Divider1DPGray400(modifier = Modifier.padding(top = 25.dp, bottom = 20.dp))
    Row {
        Text(
            text = stringResource(R.string.shopping_cart_price_label),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 25.dp)
        )
        Text(
            text = "$totalPrice€",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(end = 28.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
    Divider1DPGray400(modifier = Modifier.padding(top = 25.dp, bottom = 20.dp))

    CTAButtonGreen(
        text = stringResource(R.string.shopping_cart_cta_label),
        isCtaButtonEnabled = isCtaButtonEnabled,
        onButtonClickCallback = startCashOutProcess
    )
}

@Preview
@Composable
fun ShoppingCartHeaderPreview() {
    ShoppingCartHeader()
}

@Preview
@Composable
fun ShoppingCartListPreview() {
    ShoppingCartList(
        listOf(
            ShoppingCartItemViewModel(
                cartItemArticleData = CartItemArticleData(
                    articleId = 1,
                    articleIcon = R.drawable.article1,
                    articleName = "Kuchen",
                    articlePrice = BigDecimal(2),
                    articleQuantity = 1
                ),
                onShoppingCartStateEvent = remember { mutableStateOf(ShoppingCartStates.Initial) }
            )
        )
    )
}

@Preview
@Composable
fun ShoppingCartBottomPreview() {
    Column {
        ShoppingCartBottom(BigDecimal.valueOf(11), true, { })
    }
}

@Preview
@Composable
fun ShoppingCartContentPreview() {
    ShoppingCartContent(
        listOf(
            ShoppingCartItemViewModel(
                cartItemArticleData = CartItemArticleData(
                    articleId = 1,
                    articleIcon = R.drawable.article1,
                    articleName = "Kuchen",
                    articlePrice = BigDecimal(2),
                    articleQuantity = 1
                ),
                onShoppingCartStateEvent = remember { mutableStateOf(ShoppingCartStates.Initial) }
            )
        ),
        BigDecimal.valueOf(0.0),
        true,
        {}
    )
}
