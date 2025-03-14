package com.example.android.quotes.domain

import com.example.android.quotes.data.QuotesRepository
import com.example.android.quotes.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuotesRepository) {

    suspend operator fun invoke(): List<Quote> {

        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes)
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }

    }
}