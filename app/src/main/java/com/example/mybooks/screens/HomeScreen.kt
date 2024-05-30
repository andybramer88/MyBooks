package com.example.mybooks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { viewModel.sortBooksByYearAscending() },
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text("Sortieren nach Jahr (aufsteigend)")
                }
                Button(
                    onClick = { viewModel.sortBooksByYearDescending() },
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text("Sortieren nach Jahr (absteigend)")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            BookList(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                navController = navController,
                viewModel = viewModel,
                books = viewModel.books
            )
        }
    }
}
