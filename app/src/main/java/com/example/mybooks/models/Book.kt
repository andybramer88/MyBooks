package com.example.mybooks.models

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: String,
    val isbn: String,
    val read: Boolean
)

fun getBooks(): List<Book> {
    return listOf(
        Book(
            id = "1",
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            year = "1925",
            isbn = "9780743273565",
            read = true
        ),
        Book(
            id = "2",
            title = "To Kill a Mockingbird",
            author = "Harper Lee",
            year = "1960",
            isbn = "9780061120084",
            read = false
        ),
        Book(
            id = "3",
            title = "1984",
            author = "George Orwell",
            year = "1949",
            isbn = "9780451524935",
            read = true
        ),
        Book(
            id = "4",
            title = "Pride and Prejudice",
            author = "Jane Austen",
            year = "1813",
            isbn = "9781503290563",
            read = false
        ),
        Book(
            id = "5",
            title = "Moby-Dick",
            author = "Herman Melville",
            year = "1851",
            isbn = "9781503280786",
            read = true
        ),
        Book(
            id = "6",
            title = "War and Peace",
            author = "Leo Tolstoy",
            year = "1869",
            isbn = "9781400079988",
            read = false
        ),
        Book(
            id = "7",
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            year = "1951",
            isbn = "9780316769488",
            read = true
        ),
        Book(
            id = "8",
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            year = "1937",
            isbn = "9780547928227",
            read = true
        ),
        Book(
            id = "9",
            title = "Fahrenheit 451",
            author = "Ray Bradbury",
            year = "1953",
            isbn = "9781451673319",
            read = false
        ),
        Book(
            id = "10",
            title = "Jane Eyre",
            author = "Charlotte Brontë",
            year = "1847",
            isbn = "9780141441146",
            read = true
        ),
        Book(
            id = "11",
            title = "Brave New World",
            author = "Aldous Huxley",
            year = "1932",
            isbn = "9780060850524",
            read = false
        ),
        Book(
            id = "12",
            title = "Wuthering Heights",
            author = "Emily Brontë",
            year = "1847",
            isbn = "9780141439556",
            read = true
        ),
        Book(
            id = "13",
            title = "The Odyssey",
            author = "Homer",
            year = "8th century BC",
            isbn = "9780140268867",
            read = false
        ),
        Book(
            id = "14",
            title = "The Brothers Karamazov",
            author = "Fyodor Dostoevsky",
            year = "1880",
            isbn = "9780374528379",
            read = true
        ),
        Book(
            id = "15",
            title = "Crime and Punishment",
            author = "Fyodor Dostoevsky",
            year = "1866",
            isbn = "9780143058144",
            read = true
        ),
        Book(
            id = "16",
            title = "The Iliad",
            author = "Homer",
            year = "8th century BC",
            isbn = "9780140275360",
            read = false
        ),
        Book(
            id = "17",
            title = "The Grapes of Wrath",
            author = "John Steinbeck",
            year = "1939",
            isbn = "9780143039433",
            read = false
        ),
        Book(
            id = "18",
            title = "Ulysses",
            author = "James Joyce",
            year = "1922",
            isbn = "9780199535675",
            read = true
        ),
        Book(
            id = "19",
            title = "The Divine Comedy",
            author = "Dante Alighieri",
            year = "1320",
            isbn = "9780140448955",
            read = true
        ),
        Book(
            id = "20",
            title = "The Lord of the Rings",
            author = "J.R.R. Tolkien",
            year = "1954",
            isbn = "9780544003415",
            read = false
        )
    )
}
