package de.fred.composedemo1.shoppingcart.ui

sealed class ShoppingCartStates() {
    object Initial : ShoppingCartStates()
    data class RemoveArticleItemEvent(val articleId: Int) : ShoppingCartStates()
}