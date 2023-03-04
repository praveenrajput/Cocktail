package com.praveen.cocktail.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.praveen.cocktail.CocktailDatabase
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {

    single<SqlDriver> {
        AndroidSqliteDriver(CocktailDatabase.Schema, get(), "cocktail.db")
    }

    single {
        OkHttp.create()
    }
}
