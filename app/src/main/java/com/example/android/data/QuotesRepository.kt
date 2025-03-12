package com.example.android.data

import com.example.android.data.model.QuoteModel
import com.example.android.data.model.QuoteProvider
import com.example.android.data.network.QuoteService

class QuotesRepository {
    private val apiService = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = apiService.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}