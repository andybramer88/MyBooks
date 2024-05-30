package com.example.mybooks.models

data class Book(
    var id: String,
    var title: String,
    var author: String,
    var year: Int,
    var isbn: String,
    var read: Boolean
)
