package de.fred.composedemo1.shoppingcart.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import de.fred.composedemo1.shoppingcart.R
import de.fred.designsystem.buttons.base.BaseViewModel
import java.math.BigDecimal

class ShoppingCartViewModel : BaseViewModel<ShoppingCartViewModel>() {

    private val _shoppingCartItems = mutableStateListOf<ShoppingCartItemViewModel>()
    val shoppingCartItems: SnapshotStateList<ShoppingCartItemViewModel>
        get() = _shoppingCartItems
    val shoppingCartTotalPrice: State<BigDecimal>
        get() = mutableStateOf(_shoppingCartItems.sumOf { it.articlePrice.multiply(it.articleQuantity.toBigDecimal()) })

    val shoppingCartState = mutableStateOf<ShoppingCartStates>(ShoppingCartStates.Initial)

    init {
        Log.d("viewModel", "launch initialize")
        _shoppingCartItems.addAll(
            listOf(
                toShoppingCartItemViewModel(1, R.drawable.article1, "Flsenkaktus, 12cm", BigDecimal.valueOf(13.99), 1),
                toShoppingCartItemViewModel(2, R.drawable.article2, "Tomate \"Corazon\", 150cm", BigDecimal.valueOf(8.99), 1),
                toShoppingCartItemViewModel(3, R.drawable.article3, "Gerbera Mini, Rosa", BigDecimal.valueOf(4.95), 2),
                toShoppingCartItemViewModel(4, R.drawable.article4, "HAVSON Topf, Ton, 29 cm", BigDecimal.valueOf(18.89), 1),
            )
        )
    }

    fun removeArticleItem(articleId: Int) {
        _shoppingCartItems.firstOrNull { it.articleId == articleId }?.also {
            _shoppingCartItems.remove(it)
        }
        if (_shoppingCartItems.isEmpty()) shoppingCartState.value = ShoppingCartStates.ShoppingCartEmpty
    }

    private fun toShoppingCartItemViewModel(articleId: Int, articleIcon: Int, articleName: String, articlePrice: BigDecimal, articleQuantity: Int) =
        ShoppingCartItemViewModel(
            articleId = articleId,
            articleIcon = articleIcon,
            articleName = articleName,
            articlePrice = articlePrice,
            articleQuantity = articleQuantity,
            onRemoveArticleItem = shoppingCartState
        )
}