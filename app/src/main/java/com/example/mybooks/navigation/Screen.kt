package com.example.mybooks.navigation

const val DETAIL_ARGUMENT_KEY = "movieId" // zum testen
sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object AddChangeBookScreen : Screen("change")

}