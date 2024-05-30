package com.example.mybooks.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybooks.models.Book
import com.example.mybooks.navigation.Screen
import com.example.mybooks.viewmodels.BooksViewModel


@Composable
fun BookList(
    modifier: Modifier,
    books: List<Book>,
    navController: NavController,
    viewModel: BooksViewModel
){
    if (books.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Es wurden noch keine Bücher angelegt.")
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(books) { book ->
                BookItem(
                    book = book,
                    onDelete = { updatedBook ->
                        viewModel.deleteBook(updatedBook)
                    },
                    onMarkAsRead = { updatedBook ->
                        viewModel.updateBookState(updatedBook)
                    },
                    onEdit = {
                        navController.navigate(Screen.AddChangeBookScreen.createRoute(book.id))
                    }
                )
            }
        }
    }
}



@Composable
fun BookItem(
    book: Book,
    onDelete: (Book) -> Unit,
    onMarkAsRead: (Book) -> Unit,
    onEdit: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            BookDetails(modifier = Modifier.padding(12.dp), book = book)
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Schaltfläche zum Markieren als gelesen
                Button(
                    onClick = { onMarkAsRead(book) },
                    enabled = !book.read // Deaktiviere die Schaltfläche, wenn das Buch bereits als gelesen markiert ist
                ) {
                    Text(if (book.read) "Gelesen" else "Als gelesen markieren")
                }

                // Schaltfläche zum Löschen des Buches
                IconButton(
                    onClick = { onDelete(book) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Löschen")
                }
                // Schaltfläche zum Bearbeiten des Buches
                IconButton(
                    onClick = { onEdit() },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Bearbeiten")
                }
            }
        }
    }
}

@Composable
fun BookDetails(modifier: Modifier, book: Book) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${book.title} geschrieben von ${book.author}")
        Icon(modifier = Modifier
            .clickable {
                showDetails = !showDetails
            },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowDown
            else Icons.Default.KeyboardArrowUp, contentDescription = "show more")
    }


    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            Text(text = "ID Nummer: ${book.id}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Jahr: ${book.year}", style = MaterialTheme.typography.bodySmall)
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
            Text(text = "gelesen: ${book.read}", style = MaterialTheme.typography.bodySmall)
        }
    }
}


