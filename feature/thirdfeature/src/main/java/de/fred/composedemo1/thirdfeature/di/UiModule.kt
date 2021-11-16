package de.fred.composedemo1.thirdfeature.di

import de.fred.composedemo1.thirdfeature.ui.ThirdFeatureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module{
    viewModel { ThirdFeatureViewModel(navigator = get()) }
}