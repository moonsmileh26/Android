package com.example.android.data.network

import com.example.android.core.RetrofitHelper
import com.example.android.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}