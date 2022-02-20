package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import de.fred.designsystem.buttons.base.BaseViewModel

class ShoppingCartViewModel : BaseViewModel<ShoppingCartViewModel>() {

    private val _shoppingCartItems = mutableStateOf<List<ShoppingCartItemViewModel>>(listOf())
    val shoppingCartItems: State<List<ShoppingCartItemViewModel>>
        get() = _shoppingCartItems

    fun initialize() {
        _shoppingCartItems.value = mutableListOf(
            ShoppingCartItemViewModel("article1"),
            ShoppingCartItemViewModel("article2"),
            ShoppingCartItemViewModel("article3"),
        )
    }
}