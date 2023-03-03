package com.praveen.cocktail.di

import com.praveen.cocktail.CocktailDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
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
