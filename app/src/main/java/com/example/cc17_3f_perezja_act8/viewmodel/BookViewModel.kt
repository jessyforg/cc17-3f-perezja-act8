package com.example.cc17_3f_perezja_act8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cc17_3f_perezja_act8.BookRepository
import com.example.cc17_3f_perezja_act8.models.Book
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    private val _bookDetails = MutableLiveData<Book>()
    val bookDetails: LiveData<Book> get() = _bookDetails

    fun fetchBooks(query: String) {
        viewModelScope.launch {
            val bookList = repository.getBooks(query)
            _books.postValue(bookList)
            }
    }

    fun fetchBookDetails(id: String) {
        viewModelScope.launch {
            val book = repository.getBook(id)
            book?.let {
                _bookDetails.postValue(it)
            }
        }
    }
}
