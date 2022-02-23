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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

@Composable
fun ShoppingCartContent(viewModel: ShoppingCartViewModel) {
    val shoppingCartItemList = viewModel.shoppingCartItems
    val shoppingCartState by viewModel.shoppingCartState

    when (shoppingCartState) {
        is ShoppingCartStates.Initial -> {}
        is ShoppingCartStates.RemoveArticleItemEvent -> {
            viewModel.removeArticleItem((shoppingCartState as ShoppingCartStates.RemoveArticleItemEvent).articleId)
        }
    }
    LaunchedEffect(key1 = shoppingCartItemList) {
        viewModel.initialize()
    }
    val totalPrice = shoppingCartItemList.sumOf { it.articlePrice }
    ShoppingCartContent(itemList = shoppingCartItemList, totalPrice = totalPrice)
}

@Composable
fun ShoppingCartContent(itemList: List<ShoppingCartItemViewModel>, totalPrice: BigDecimal) {
    Column {
        LazyColumn {
            items(items = itemList) { item ->
                ItemBackground {
                    Row(modifier = Modifier.size(width = maxWidth, height = maxHeight), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_dialog_info),
                            contentDescription = "",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Column(modifier = Modifier.padding(start = 32.dp)) {
                            Text(item.articleName)
                            Row() {
                                Text("${item.articleQuantity} ")
                                Text("${item.articlePrice}€")
                            }
                        }
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_delete),
                            contentDescription = "",
                            alignment = Alignment.CenterEnd,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    item.removeArticleItem()
                                }
                        )
                    }
                }
            }
        }

        Text("Total Price: $totalPrice")

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(176, 213, 83))
        ) {
            Text(text = "check out")
        }
    }
}

@Composable
fun ItemBackground(content: @Composable BoxWithConstraintsScope.() -> Unit) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 25.dp, end = 25.dp, bottom = 22.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(241, 240, 247))
    ) {
        content()
    }
}

@Preview
@Composable
fun ShoppingCartContentPreview() {
    ShoppingCartContent(
        listOf(ShoppingCartItemViewModel(
            articleId = 1,
            articleIcon = 0,
            articleName = "Kuchen",
            articlePrice = BigDecimal(2),
            articleQuantity = 1,
            onRemoveArticleItem = null
        )), BigDecimal.valueOf(0.0))
}
