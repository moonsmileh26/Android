package com.example.android.domain.model

import com.example.android.data.database.entities.QuoteEntity
import com.example.android.data.model.QuoteModel

data class Quote(val quote: String, val author: String)

fun QuoteModel.toDomain(): Quote = Quote(quote, author)

fun QuoteEntity.toDomain(): Quote = Quote(quote, author)