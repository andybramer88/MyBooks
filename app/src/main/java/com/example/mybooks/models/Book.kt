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

// Initialisiere eine leere Liste von Büchern
val bookList = mutableStateListOf<Book>()

// Funktion zum Hinzufügen eines neuen Buches zur Liste
fun addBook(title: String,
            author: String,
            year: Int,
            isbn: String,
            read: Boolean) {
    val id = bookList.size.toString() // Generiere eine eindeutige ID für das Buch
    val newBook = Book(id, title, author, year, isbn, read)
    bookList.add(newBook) // Füge das neue Buch zur Liste hinzu
}

fun getBooks(): List<Book> {
    return if (bookList.isEmpty()) {
        // Wenn die Buchliste leer ist, gib eine leere Liste zurück
        emptyList()
    } else {
        // Andernfalls gib die Buchliste zurück
        bookList
    }
}
