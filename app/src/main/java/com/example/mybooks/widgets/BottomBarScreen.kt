package com.example.mybooks.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mybooks.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = Screen.HomeScreen.route,
        title = "Meine Lieblingsbücher",
        icon = Icons.Filled.List
    )

    object Change: BottomBarScreen(
        route = Screen.AddChangeBookScreen.route,
        title = "Neues Buch anlegen/bearbeiten",
        icon = Icons.Filled.Add
    )
}