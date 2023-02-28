package com.praveen.cocktail.data.network.api

import com.praveen.cocktail.models.Drinks
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

class CocktailApiImpl(private val httpClient: HttpClient) : CocktailApiInterface {

    override suspend fun getDrinksApi(): Drinks {
        return httpClient.get {
            drinks("api/json/v1/1/search.php?f=a")
        }.body()
    }

    private fun HttpRequestBuilder.drinks(path: String) {
        url {
            takeFrom("https://www.thecocktaildb.com/")
            encodedPath = path
        }
    }
}
