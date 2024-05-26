package com.example.mybooks.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybooks.widgets.AddChangeBookForm
import com.example.mybooks.widgets.SimpleBottomAppBar
import com.example.mybooks.widgets.SimpleTopAppBar

@Composable
fun AddChangeBookScreen(navController: NavController) {
    Scaffold(
        topBar = {
            SimpleTopAppBar("Buch anlegen/ändern")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 16.dp) // Abstand von der TopAppBar
        ) {
            AddChangeBookForm(navController = navController)
        }
    }
}
