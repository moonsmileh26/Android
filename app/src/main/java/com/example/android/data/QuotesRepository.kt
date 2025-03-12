package com.example.android.data

import com.example.android.data.model.QuoteModel
import com.example.android.data.model.QuoteProvider
import com.example.android.data.network.QuoteService
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val apiService: QuoteService,
    private val quoteProvider: QuoteProvider
) {
    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = apiService.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}