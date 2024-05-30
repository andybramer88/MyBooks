package com.example.mybooks.models

import androidx.compose.runtime.mutableStateListOf

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String,
    val read: Boolean
)
