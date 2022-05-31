package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.runtime.MutableState
import de.fred.designsystem.cart.CartItemArticleData

class ShoppingCartItemViewModel(
    override val cartItemArticleData: CartItemArticleData,
    val onShoppingCartStateEvent: MutableState<ShoppingCartStates>,
) : CartItemSuperViewModel(cartItemArticleData) {

    override fun removeArticleItemFromShoppingCart() {
        super.removeArticleItemFromShoppingCart()
        onShoppingCartStateEvent.value = ShoppingCartStates.RemoveArticleItemFromShoppingCartEvent(cartItemArticleData.articleId)
    }
}