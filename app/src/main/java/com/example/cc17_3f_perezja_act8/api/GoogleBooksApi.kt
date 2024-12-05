package com.example.cc17_3f_perezja_act8.api

import com.example.cc17_3f_perezja_act8.models.BookResponse
import com.example.cc17_3f_perezja_act8.models.Book
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResult: Int = 20): BookResponse

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id:String):Book
}

