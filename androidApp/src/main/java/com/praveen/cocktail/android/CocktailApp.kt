package com.praveen.cocktail.android

import android.app.Application
import com.praveen.cocktail.android.di.appModule
import com.praveen.cocktail.di.initKoin
import org.koin.android.ext.koin.androidContext

class CocktailApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CocktailApp)
            modules(appModule)
        }
    }
}
