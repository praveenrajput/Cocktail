package com.praveen.cocktail.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Drinks(
    @SerialName("drinks")
    val drinks: List<Drink> = listOf()
)
