package de.fred.composedemo1

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single { Navigator() }
    viewModel{ MainViewModel() }
    viewModel{ SecondScreenViewModel() }
}