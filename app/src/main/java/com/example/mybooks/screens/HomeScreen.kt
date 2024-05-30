package com.example.mybooks.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.widgets.BookList
import com.example.mybooks.widgets.SimpleBottomAppBar
import com.example.mybooks.widgets.SimpleTopAppBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BooksViewModel
) {
    Scaffold (
        topBar = {
            SimpleTopAppBar("MyBooks")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ){ innerPadding ->
        BookList(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            viewModel = viewModel,
            books = viewModel.books

        )
    }
}
