package com.example.cc17_3f_perezja_act8

import com.example.cc17_3f_perezja_act8.api.GoogleBooksApi
import com.example.cc17_3f_perezja_act8.models.Book

class BookRepository(private val api: GoogleBooksApi) {
    suspend fun getBooks(query: String): List<Book> {
        val response = api.getBooks(query, startIndex = 0, maxResult = 20)
        return response.items ?: emptyList()
    }

    suspend fun getBook(id: String): Book? {
        return try {
            api.getBook(id)
        } catch (e: Exception) {
            null
        }
    }
}
