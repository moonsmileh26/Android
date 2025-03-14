package com.example.android.quotes.data.network

import com.example.android.quotes.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}