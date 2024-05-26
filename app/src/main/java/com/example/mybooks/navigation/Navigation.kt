package com.example.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybooks.screens.AddChangeBookScreen
import com.example.mybooks.screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.HomeScreen.route) {  // pass a start destination
        composable(route = Screen.HomeScreen.route){   // route with name "homescreen" navigates to HomeScreen composable
            HomeScreen(navController = navController)
        }
        composable(route = Screen.AddChangeBookScreen.route){
            AddChangeBookScreen(navController = navController)
        }
    }
}