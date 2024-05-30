package com.example.mybooks.widgets

import android.icu.util.Calendar
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybooks.viewmodels.BooksViewModel

@Composable
fun AddChangeBookForm(
    navController: NavController,
    viewModel: BooksViewModel,
    bookId: String?
) {
    var existingTitle: String = ""
    var existingAuthor: String = ""
    var existingYear: String = ""
    var existingIsbn: String = ""
    var existingRead: Boolean = false

    if (bookId != null) {
        if (viewModel.bookExists(bookId)) run {
            var book = viewModel.getBookById(bookId)
            if (book != null) {
                existingTitle = book.title
            }
            if (book != null) {
                existingAuthor = book.author
            }
            if (book != null) {
                existingYear = book.year.toString()
            }
            if (book != null) {
                existingIsbn = book.isbn
            }
            if (book != null) {
                existingRead = book.read
            }
        }
    }
    var title by remember { mutableStateOf(existingTitle) }
    var author by remember { mutableStateOf(existingAuthor) }
    var year by remember { mutableStateOf(existingYear) }
    var isbn by remember { mutableStateOf(existingIsbn) }
    var read by remember { mutableStateOf(existingRead) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun validateInput(): Boolean {
        return when {
            title.isBlank() -> {
                errorMessage = "Der Titel darf nicht leer sein."
                false
            }
            author.isBlank() -> {
                errorMessage = "Der Autor darf nicht leer sein."
                false
            }
            year.toIntOrNull() == null || year.toInt() > Calendar.getInstance().get(Calendar.YEAR) -> {
                errorMessage = "Das Jahr muss gültig sein und darf nicht in der Zukunft liegen."
                false
            }
            !viewModel.isValidISBN(isbn) -> {
                errorMessage = "Die ISBN ist nicht gültig."
                false
            }
            else -> {
                errorMessage = null
                true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titel") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Jahr der Erstveröffentlichung") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Gelesen")
            Switch(
                checked = read,
                onCheckedChange = { read = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                if (validateInput()) {
                    if (bookId != null) {
                        if (viewModel.bookExists(bookId)) {
                            viewModel.editBook(
                                id = bookId,
                                isbn = isbn,
                                author = author,
                                title = title,
                                year = year.toInt(),
                                read = read
                            )
                        } else {
                            viewModel.addBook(title, author, year.toInt(), isbn, read)
                        }
                    }
                    navController.navigateUp() // Zurück zur vorherigen Seite navigieren
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Speichern")
        }
    }
}
