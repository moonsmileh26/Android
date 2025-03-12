package com.example.android.domain

import com.example.android.data.QuotesRepository
import com.example.android.domain.model.Quote
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