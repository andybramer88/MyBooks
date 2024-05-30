package com.example.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mybooks.screens.AddChangeBookScreen
import com.example.mybooks.screens.HomeScreen
import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.widgets.AddChangeBookForm

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance
    val viewModel = BooksViewModel()

    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.HomeScreen.route) {  // pass a start destination
        composable(route = Screen.HomeScreen.route){   // route with name "homescreen" navigates to HomeScreen composable
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType}),
            route = Screen.AddChangeBookScreen.route){ backStackEntry ->
            AddChangeBookScreen(bookId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY),
                navController = navController, booksViewModel = viewModel)
        }
    }
}