package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.runtime.MutableState

class ShoppingShoppingCartItemViewModel(
    override val cartItemArticleData: CartItemArticleData,
    val onShoppingCartStateEvent: MutableState<ShoppingCartStates>,
) : ShoppingCartItemSuperViewModel(cartItemArticleData) {

    override fun removeArticleItemFromShoppingCart() {
        onShoppingCartStateEvent.value = ShoppingCartStates.RemoveArticleItemFromShoppingCartEvent(cartItemArticleData.articleId)
    }
}