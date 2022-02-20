package de.fred.composedemo1.shoppingcart.di

import de.fred.composedemo1.shoppingcart.ui.ShoppingCartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ShoppingCartViewModel() }
}