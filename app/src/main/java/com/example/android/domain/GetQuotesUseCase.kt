package com.example.android.domain

import com.example.android.data.QuotesRepository
import com.example.android.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuotesRepository) {
    suspend operator fun invoke(): List<QuoteModel> = repository.getAllQuotes()
}