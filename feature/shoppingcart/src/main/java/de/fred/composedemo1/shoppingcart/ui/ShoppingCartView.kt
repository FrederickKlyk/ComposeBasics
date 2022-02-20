package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue


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
    LazyColumn() {
        items(items = itemList) { item ->
            Text(text = item.article)
        }
    }
}