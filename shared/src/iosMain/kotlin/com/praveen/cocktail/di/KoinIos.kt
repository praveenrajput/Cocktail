package com.praveen.cocktail.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.praveen.cocktail.CocktailDatabase
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(CocktailDatabase.Schema, "cocktail.db")
    }
    single {
        Darwin.create()
    }
}
