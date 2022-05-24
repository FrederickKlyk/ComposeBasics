package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.runtime.MutableState
import java.math.BigDecimal

class ShoppingCartItemViewModel(
    override val cartItemArticleData: CartItemArticleData,
    val onShoppingCartStateEvent: MutableState<ShoppingCartStates>,
) : CartItemSuperViewModel(cartItemArticleData) {

    override fun removeArticleItemFromShoppingCart() {
        onShoppingCartStateEvent.value = ShoppingCartStates.RemoveArticleItemFromShoppingCartEvent(cartItemArticleData.articleId)
    }
}