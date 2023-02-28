package com.praveen.cocktail.data.respository

import com.praveen.cocktail.Drink
import kotlinx.coroutines.flow.Flow

interface CocktailRepositoryInterface {
    suspend fun getDrinks(): Flow<List<Drink>>
}
