package de.fred.composedemo1.shoppingcart.ui

import de.fred.designsystem.cart.CartItemArticleData
import timber.log.Timber

abstract class CartItemSuperViewModel(open val cartItemArticleData: CartItemArticleData) {

    open fun removeArticleItemFromShoppingCart(){
        Timber.d("${cartItemArticleData.articleId} was removed!")
    }
}



