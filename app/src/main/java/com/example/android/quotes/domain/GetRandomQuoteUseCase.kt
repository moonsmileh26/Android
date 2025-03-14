package com.example.android.quotes.domain

import com.example.android.quotes.data.QuotesRepository
import com.example.android.quotes.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quotesRepository: QuotesRepository) {
    suspend operator fun invoke(): Quote? {
        val quotes = quotesRepository.getAllQuotesFromDatabase()
        if (quotes.isNotEmpty()) {
            return quotes.random()
        }
        return null
    }
}