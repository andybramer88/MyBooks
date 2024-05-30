package com.example.mybooks.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.widgets.BookList
import com.example.mybooks.widgets.SimpleBottomAppBar
import com.example.mybooks.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BooksViewModel
) {
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SimpleTopAppBar("MyBooks")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    label = { Text("Suchen") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { viewModel.filterBooks(searchQuery.value) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Suchen")
                }
            }
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