package com.example.android.quotes.data

import com.example.android.quotes.data.database.dao.QuoteDao
import com.example.android.quotes.data.database.entities.QuoteEntity
import com.example.android.quotes.data.network.QuoteService
import com.example.android.quotes.domain.model.Quote
import com.example.android.quotes.domain.model.toDomain
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val apiService: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = apiService.getQuotes().map { it.toDomain() }
        return response
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<Quote>) {
        val quoteEntities = quotes.map { QuoteEntity(quote = it.quote, author = it.author) }
        quoteDao.insertAll(quoteEntities)

    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}