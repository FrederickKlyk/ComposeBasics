package de.fred.composedemo1.shoppingcart.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import de.fred.composedemo1.shoppingcart.R
import de.fred.designsystem.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.math.BigDecimal

class ShoppingCartViewModel : BaseViewModel<ShoppingCartViewModel>() {

    private val _shoppingCartItems = mutableStateListOf<ShoppingShoppingCartItemViewModel>()
    val shoppingCartItems: SnapshotStateList<ShoppingShoppingCartItemViewModel>
        get() = _shoppingCartItems
    val shoppingCartTotalPriceState: State<BigDecimal>
        get() = mutableStateOf(_shoppingCartItems.sumOf {
            it.cartItemArticleData.articlePrice.multiply(it.cartItemArticleData.articleQuantity.toBigDecimal())
        })

    private val onShoppingCartStateEvent = mutableStateOf<ShoppingCartStates>(ShoppingCartStates.Initial)
    val isCtaButtonEnabledState: State<Boolean>
        get() = handleShoppingCartState().run { mutableStateOf(!_shoppingCartItems.isEmpty()) }

    init {
        Log.d("viewModel", "launch initialize")
        _shoppingCartItems.addAll(shoppingCartItemsFakeRepository())
    }

    fun startCashOutProcess() {
        Log.d("shoppingCart", "cashOut Button clicked")
        //TODO: TBD
    }

    private fun handleShoppingCartState() = snapshotFlow { onShoppingCartStateEvent.value }
        .onEach { shoppingCartStateEvent ->
            when (shoppingCartStateEvent) {
                is ShoppingCartStates.Initial -> {
                    // nothing to do
                }
                is ShoppingCartStates.RemoveArticleItemFromShoppingCartEvent -> {
                    removeArticleItemFromShoppingCart(shoppingCartStateEvent.articleId)
                }
            }
        }.launchIn(viewModelScope)

    private fun removeArticleItemFromShoppingCart(articleId: Int) {
        _shoppingCartItems.firstOrNull { it.cartItemArticleData.articleId == articleId }?.also {
            _shoppingCartItems.remove(it)
        }
    }

    private fun shoppingCartItemsFakeRepository() = listOf(
        toShoppingCartItemViewModel(1, R.drawable.article1, "Flsenkaktus, 12cm", BigDecimal.valueOf(13.99), 1),
        toShoppingCartItemViewModel(2, R.drawable.article2, "Tomate \"Corazon\", 150cm", BigDecimal.valueOf(8.99), 1),
        toShoppingCartItemViewModel(3, R.drawable.article3, "Gerbera Mini, Rosa", BigDecimal.valueOf(4.95), 2),
        toShoppingCartItemViewModel(4, R.drawable.article4, "HAVSON Topf, Ton, 29 cm", BigDecimal.valueOf(18.89), 1),
    )

    private fun toShoppingCartItemViewModel(articleId: Int, articleIcon: Int, articleName: String, articlePrice: BigDecimal, articleQuantity: Int) =
        ShoppingShoppingCartItemViewModel(
            cartItemArticleData = CartItemArticleData(
                articleId = articleId,
                articleIcon = articleIcon,
                articleName = articleName,
                articlePrice = articlePrice,
                articleQuantity = articleQuantity,
            ),
            onShoppingCartStateEvent = onShoppingCartStateEvent
        )
}