package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShoppingCartContent(viewModel: ShoppingCartViewModel) {
    val shoppingCartItemList by viewModel.shoppingCartItems
    LaunchedEffect(key1 = shoppingCartItemList) {
        viewModel.initialize()
    }
    ShoppingCartContent(itemList = shoppingCartItemList)
}

@Composable
fun ShoppingCartContent(itemList: List<ShoppingCartItemViewModel>) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(items = itemList) { item ->
            ItemBackground {
                Row(Modifier.padding(start = 8.dp, top = 8.dp)) {
                    Column() {
                        Text(text = item.article)
                        Text(text = item.article)
                    }
                    Text(modifier = Modifier.padding(8.dp), text = "test")
                }
            }
        }
    }
}

@Composable
fun ItemBackground(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(50, 50, 50, 50))
    ){
        content()
    }
}

@Preview
@Composable
fun ShoppingCartContentPreview() {
    ShoppingCartContent(listOf(ShoppingCartItemViewModel("1")))
}
