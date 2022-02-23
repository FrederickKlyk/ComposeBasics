package de.fred.composedemo1.shoppingcart.ui

import androidx.compose.runtime.MutableState
import java.math.BigDecimal

class ShoppingCartItemViewModel(
    val articleId: Int,
    val articleIcon: Int,
    val articleName: String,
    val articlePrice: BigDecimal,
    val articleQuantity: Int,
    val onRemoveArticleItem: MutableState<ShoppingCartStates>?,
) {

    fun removeArticleItem() {
        onRemoveArticleItem?.value = ShoppingCartStates.RemoveArticleItemEvent(articleId)
    }
}