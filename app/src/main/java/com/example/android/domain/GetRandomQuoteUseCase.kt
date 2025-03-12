package com.example.android.domain

import com.example.android.data.model.QuoteModel
import com.example.android.data.model.QuoteProvider

class GetRandomQuoteUseCase {
    operator fun invoke(): QuoteModel? {
        val quotes = QuoteProvider.quotes
        if (quotes.isNotEmpty()) {
            return quotes.random()
        }
        return null
    }
}