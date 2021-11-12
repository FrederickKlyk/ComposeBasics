package de.fred.composedemo1

import android.app.Application
import de.fred.composedemo1.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import de.fred.composedemo1.secondfeature.di.uiModule as secondFeatureUiModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(uiModule, secondFeatureUiModule)
        }
    }
}