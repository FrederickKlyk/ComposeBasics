package de.fred.composedemo1.di

import de.fred.composedemo1.navigation.ThirdFeatureExternalNavigationImpl
import de.fred.composedemo1.thirdfeature.navigation.ThirdFeatureExternalNavigation
import org.koin.dsl.module

val externalNavigationModule = module {
    single<ThirdFeatureExternalNavigation> { ThirdFeatureExternalNavigationImpl(navigator = get()) }
}