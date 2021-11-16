package de.fred.composedemo1.secondfeature.di

import androidx.lifecycle.SavedStateHandle
import de.fred.composedemo1.secondfeature.ui.SecondFeatureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { (savedStateHandle: SavedStateHandle) -> SecondFeatureViewModel(savedStateHandle, get()) }
}