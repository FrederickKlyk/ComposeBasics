package de.fred.composedemo1.navigation.di

import de.fred.composedemo1.navigation.Navigator
import org.koin.dsl.module

val navigationModule = module {
    single { Navigator() }
}