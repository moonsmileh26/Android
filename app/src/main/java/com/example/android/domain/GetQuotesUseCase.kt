package com.example.android.domain

import com.example.android.data.QuotesRepository
import com.example.android.data.model.QuoteModel

class GetQuotesUseCase {
    private val repository = QuotesRepository()

    suspend operator fun invoke(): List<QuoteModel> = repository.getAllQuotes()

}