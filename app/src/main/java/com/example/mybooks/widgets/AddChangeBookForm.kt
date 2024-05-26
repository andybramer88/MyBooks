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
import com.example.mybooks.models.addBook

@Composable
fun AddChangeBookForm(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var read by remember { mutableStateOf(false) }
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
            !isValidISBN(isbn) -> {
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
                    // Rufe die Funktion addBook auf und übergebe die eingegebenen Daten
                    addBook(title, author, year.toInt(), isbn, read)
                    navController.navigateUp() // Zurück zur vorherigen Seite navigieren
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Speichern")
        }
    }
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
