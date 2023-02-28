package com.praveen.cocktail.data.network.api

import com.praveen.cocktail.models.Drinks

interface CocktailApiInterface {
    suspend fun getDrinksApi(): Drinks
}
