package com.praveen.cocktail.models

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val name: String = "",
    val quantity: String = ""
)
