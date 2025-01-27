package com.example.cc17_3f_perezja_act8

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cc17_3f_perezja_act8.api.GoogleBooksApi
import com.example.cc17_3f_perezja_act8.models.Book
import com.example.cc17_3f_perezja_act8.viewmodel.BookViewModel
import com.example.cc17_3f_perezja_act8.viewmodel.BookViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), BookAdapter.OnItemClickListener {
    private lateinit var viewModel: BookViewModel
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleBooksApi::class.java)

        val repository = BookRepository(api)
        val factory = BookViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[BookViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.books.observe(this) { books ->
            adapter = BookAdapter(books, this)
            recyclerView.adapter = adapter
        }

        viewModel.bookDetails.observe(this) { book ->
            Toast.makeText(this, "Title: ${book.volumeInfo.title}\nAuthors: ${book.volumeInfo.authors?.joinToString()}", Toast.LENGTH_LONG).show()
        }

        viewModel.fetchBooks("Mystery Novels")
    }


    override fun onItemClick(book: Book) {
        viewModel.fetchBookDetails(book.id)
    }
}
