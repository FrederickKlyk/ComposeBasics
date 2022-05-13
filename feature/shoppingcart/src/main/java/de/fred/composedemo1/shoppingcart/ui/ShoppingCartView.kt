package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import de.fred.designsystem.divider.Dividers.Divider1DPGray400
import de.fred.designsystem.text.Text.Text11sp
import java.math.BigDecimal

@Composable
fun ShoppingCartContent(viewModel: ShoppingCartViewModel) {
    val shoppingCartItemList = viewModel.shoppingCartItems
    val shoppingCartState by viewModel.shoppingCartState
    val totalPrice by viewModel.shoppingCartTotalPrice

    val ctaButtonEnabled: Boolean = when (shoppingCartState) {
        is ShoppingCartStates.Initial -> {
            true
        }
        is ShoppingCartStates.RemoveArticleItemEvent -> {
            viewModel.removeArticleItem((shoppingCartState as ShoppingCartStates.RemoveArticleItemEvent).articleId)
            true
        }
        is ShoppingCartStates.ShoppingCartEmpty -> false
    }

    ShoppingCartContent(
        itemList = shoppingCartItemList,
        totalPrice = totalPrice,
        ctaButtonEnabled = ctaButtonEnabled,
        startCashOutProcess = viewModel::startCashOutProcess
    )
}


@Composable
fun ShoppingCartContent(
    itemList: List<ShoppingCartItemViewModel>,
    totalPrice: BigDecimal,
    ctaButtonEnabled: Boolean,
    startCashOutProcess: () -> Unit,
) {
    Column {
        ShoppingCartHeader()
        ShoppingCartList(itemList)
        ShoppingCartBottom(totalPrice, ctaButtonEnabled, startCashOutProcess)
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
fun ShoppingCartList(itemList: List<ShoppingCartItemViewModel>) {
    LazyColumn(modifier = Modifier.padding(top = 32.dp, start = 25.dp)) {
        items(items = itemList) { item ->
            ShoppingCartItemBackground {
                Image(
                    painter = painterResource(id = item.articleIcon),
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
                        Text11sp(text = item.articleName)
                        Row(modifier = Modifier.align(Alignment.End)) {
                            Text11sp(text = "${item.articleQuantity}x ")
                            Text11sp(
                                text = "${item.articlePrice}€",
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
                                item.removeArticleItem()
                            }
                            .size(width = 24.dp, height = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnScope.ShoppingCartBottom(totalPrice: BigDecimal, ctaButtonEnabled: Boolean, startCashOutProcess: () -> Unit) {
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
        ctaButtonEnabled = ctaButtonEnabled,
        onButtonClickCallback = startCashOutProcess
    )
}

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
        content()
    }
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
                articleId = 1,
                articleIcon = R.drawable.article1,
                articleName = "Kuchen",
                articlePrice = BigDecimal(2),
                articleQuantity = 1,
                onRemoveArticleItem = null
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
                articleId = 1,
                articleIcon = R.drawable.article1,
                articleName = "Kuchen",
                articlePrice = BigDecimal(2),
                articleQuantity = 1,
                onRemoveArticleItem = null
            )
        ),
        BigDecimal.valueOf(0.0),
        true,
        {}
    )
}
