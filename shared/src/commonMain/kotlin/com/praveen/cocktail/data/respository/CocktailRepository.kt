package com.praveen.cocktail.data.respository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.praveen.cocktail.CocktailDatabase
import com.praveen.cocktail.Drink
import com.praveen.cocktail.DrinksQueries
import com.praveen.cocktail.data.network.api.CocktailApiImpl
import com.praveen.cocktail.models.Ingredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CocktailRepository : CocktailRepositoryInterface, KoinComponent {
    private val cocktailApiImpl: CocktailApiImpl by inject()
    private val cocktailDatabase: CocktailDatabase by inject()

    private val drinksQueries: DrinksQueries = cocktailDatabase.drinksQueries

    override suspend fun getDrinks(): Flow<List<Drink>> {
        refreshDrinks()
        return drinksQueries.selectAllDrinks().asFlow().mapToList(Dispatchers.Default)
    }

    private suspend fun refreshDrinks() {
        try {
            val drinksData = cocktailApiImpl.getDrinksApi()
            drinksQueries.transaction {
                drinksQueries.deleteAllDrinks()
                drinksData.drinks.forEach {
                    drinksQueries.insertDrink(
                        Drink(
                            id = it.idDrink ?: "",
                            name = it.strDrink ?: "",
                            thumb = it.strDrinkThumb ?: "",
                            category = it.strCategory ?: "",
                            alcoholic = it.strAlcoholic == "Alcoholic",
                            instructions = it.strInstructions ?: "",
                            ingredients = getIngredientsListFromDrink(it),
                            dateModified = it.dateModified ?: "",
                        ),
                    )
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun getIngredientsListFromDrink(
        drink: com.praveen.cocktail.models.Drink,
    ): List<Ingredient> {
        return drink.run {
            mutableListOf<Ingredient>().apply {
                addIngredient(strIngredient1, strMeasure1)
                addIngredient(strIngredient2, strMeasure2)
                addIngredient(strIngredient3, strMeasure3)
                addIngredient(strIngredient4, strMeasure4)
                addIngredient(strIngredient5, strMeasure5)
                addIngredient(strIngredient6, strMeasure6)
                addIngredient(strIngredient7, strMeasure7)
                addIngredient(strIngredient8, strMeasure8)
                addIngredient(strIngredient9, strMeasure9)
                addIngredient(strIngredient10, strMeasure10)
                addIngredient(strIngredient11, strMeasure11)
                addIngredient(strIngredient12, strMeasure12)
                addIngredient(strIngredient13, strMeasure13)
                addIngredient(strIngredient14, strMeasure14)
                addIngredient(strIngredient15, strMeasure15)
            }
        }
    }

    private fun MutableList<Ingredient>.addIngredient(name: String?, quantity: String?) {
        if (name?.isNotBlank() == true) {
            add(Ingredient(name, quantity ?: ""))
        }
    }
}
