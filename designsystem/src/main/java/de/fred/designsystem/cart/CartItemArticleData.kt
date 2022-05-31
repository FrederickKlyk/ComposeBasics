package de.fred.designsystem.cart

import java.math.BigDecimal

data class CartItemArticleData(
    val articleId: Int,
    val articleIcon: Int,
    val articleName: String,
    val articlePrice: BigDecimal,
    val articleQuantity: Int,
)