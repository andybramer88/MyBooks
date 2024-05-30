package com.example.mybooks.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mybooks.models.Book

class BooksViewModel: ViewModel() {
    private val _books = mutableStateListOf<Book>()
    private val _filteredBooks = mutableStateListOf<Book>()

    val books: List<Book>
        get() = if (_filteredBooks.isEmpty()) _books else _filteredBooks

    fun addBook(title: String, author: String, year: Int, isbn: String, read: Boolean) {
        val id = _books.size.toString() // Generiere eine eindeutige ID für das Buch
        val newBook = Book(id, title, author, year, isbn, read)
        _books.add(newBook) // Füge das neue Buch zur Liste hinzu
        filterBooks("") // Zurücksetzen des Filters
    }

    fun updateBookState(updatedBook: Book) {
        val bookIndex = _books.indexOfFirst { it.id == updatedBook.id }
        if (bookIndex != -1) {
            _books[bookIndex] = updatedBook.copy(read = !updatedBook.read)
            filterBooks("") // Zurücksetzen des Filters
        }
    }

    fun deleteBook(book: Book) {
        _books.remove(book)
        filterBooks("") // Zurücksetzen des Filters
    }

    fun isValidISBN(isbn: String): Boolean {
        // Entfernen der Bindestriche
        val sanitizedISBN = isbn.replace("-", "")

        // Prüfen der Länge + ob alles Zahlen sind
        if (sanitizedISBN.length != 13 || !sanitizedISBN.all { it.isDigit() }) {
            return false
        }

        // Algorithmus zum Check
        val checkDigit = sanitizedISBN.last().toString().toInt()
        val sum = sanitizedISBN.take(12).mapIndexed { index, c ->
            val digit = c.toString().toInt()
            if (index % 2 == 0) digit else digit * 3
        }.sum()

        val remainder = sum % 10
        val calculatedCheckDigit = if (remainder == 0) 0 else 10 - remainder

        return checkDigit == calculatedCheckDigit
    }

    fun sortBooksByYearAscending() {
        _books.sortBy { it.year }
        filterBooks("") // Zurücksetzen des Filters
    }

    fun sortBooksByYearDescending() {
        _books.sortByDescending { it.year }
        filterBooks("") // Zurücksetzen des Filters
    }

    fun filterBooks(query: String) {
        val lowerCaseQuery = query.lowercase()
        _filteredBooks.clear()
        if (lowerCaseQuery.isBlank()) {
            _filteredBooks.addAll(_books)
        } else {
            _filteredBooks.addAll(_books.filter {
                it.title.lowercase().contains(lowerCaseQuery) ||
                        it.author.lowercase().contains(lowerCaseQuery)
            })
        }
    }
}