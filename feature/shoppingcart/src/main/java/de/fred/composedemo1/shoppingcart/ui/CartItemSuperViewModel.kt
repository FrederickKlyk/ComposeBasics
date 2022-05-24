package de.fred.composedemo1.shoppingcart.ui

import java.math.BigDecimal

abstract class CartItemSuperViewModel(open val cartItemArticleData: CartItemArticleData) {

    abstract fun removeArticleItemFromShoppingCart()
}

data class CartItemArticleData(
    val articleId: Int,
    val articleIcon: Int,
    val articleName: String,
    val articlePrice: BigDecimal,
    val articleQuantity: Int,
)