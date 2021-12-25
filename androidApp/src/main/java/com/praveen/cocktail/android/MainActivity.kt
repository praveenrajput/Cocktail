package com.praveen.cocktail.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.praveen.cocktail.Greeting
import com.praveen.cocktail.android.ui.theme.CocktailTheme

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Text(text = greet())
                }
            }
        }
    }
}
