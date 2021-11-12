package de.fred.composedemo1.di

import de.fred.composedemo1.ui.MainViewModel
import de.fred.composedemo1.ui.SecondScreenViewModel
import de.fred.composedemo1.ui.utils.Navigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single { Navigator() }
    viewModel{ MainViewModel() }
    viewModel{ SecondScreenViewModel() }
}