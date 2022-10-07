package de.fred.composedemo1.flows.di

import de.fred.composedemo1.flows.ui.FlowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { FlowsViewModel(navigator = get()) }
}