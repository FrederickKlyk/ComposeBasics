package de.fred.composedemo1.di

import de.fred.composedemo1.ui.MainViewModel
import de.fred.composedemo1.ui.DetailScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MainViewModel(navigator = get()) }
    viewModel { DetailScreenViewModel(navigator = get()) }
}