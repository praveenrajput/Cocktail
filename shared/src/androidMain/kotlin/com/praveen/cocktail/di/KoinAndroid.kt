package com.praveen.cocktail.di

import com.praveen.cocktail.CocktailDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
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
