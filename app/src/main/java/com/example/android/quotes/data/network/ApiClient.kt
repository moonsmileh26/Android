package com.example.android.quotes.data.network

import com.example.android.quotes.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("getAllQuotes.json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>

}