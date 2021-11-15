package de.fred.composedemo1.secondfeature.di

import de.fred.composedemo1.secondfeature.ui.SecondFeatureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { params -> SecondFeatureViewModel(params.get(), get()) }
}