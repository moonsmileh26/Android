package com.example.android.quotes.domain.model

import com.example.android.quotes.data.database.entities.QuoteEntity
import com.example.android.quotes.data.model.QuoteModel


data class Quote(val quote: String, val author: String)

fun QuoteModel.toDomain(): Quote = Quote(quote, author)

fun QuoteEntity.toDomain(): Quote = Quote(quote, author)