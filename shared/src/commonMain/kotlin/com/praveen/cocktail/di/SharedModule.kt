package com.praveen.cocktail.di

import com.praveen.cocktail.CocktailDatabase
import com.praveen.cocktail.data.network.api.CocktailApiImpl
import com.praveen.cocktail.data.respository.CocktailRepository
import com.praveen.cocktail.models.Ingredient
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModules(), platformModule)
}

fun initKoin() = initKoin { }

fun commonModules() = module {
    single { createJson() }
    single { createHttpClient(get(), get()) }
    single { createCocktailApi(get()) }
    single { createDatabase(get()) }
    single { createCocktailRepository() }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                }
            }
        }
    }

expect val platformModule: Module

fun createCocktailApi(httpClient: HttpClient) = CocktailApiImpl(httpClient)

fun createDatabase(sqlDriver: SqlDriver) = CocktailDatabase(
    sqlDriver,
    DrinkAdapter = com.praveen.cocktail.Drink.Adapter(
        ingredientsAdapter = ingredientAdapter,
    ),
)

fun provideCocktailQueries(cocktailDatabase: CocktailDatabase) = cocktailDatabase.drinksQueries

fun createCocktailRepository() = CocktailRepository()

val ingredientAdapter = object : ColumnAdapter<List<Ingredient>, String> {
    override fun decode(databaseValue: String): List<Ingredient> {
        return if (databaseValue.isBlank()) {
            listOf()
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: List<Ingredient>): String {
        return Json.encodeToString(value)
    }
}
