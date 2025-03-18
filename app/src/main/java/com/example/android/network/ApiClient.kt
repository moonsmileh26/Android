package com.example.android.network

import com.example.android.login.data.LoginResponse
import com.example.android.quotes.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("618a6bc8f53831bcbd22d76013158809/raw/fd8684f2e0cb25c6c898e375c6dacc4b85efdcc6/getAllQuotes.json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>

    @GET("d91b1c03f661d06389c3e0ff7793d432/raw/b2eebd798a13340e2f5ceb27540a1184602e1605/doLogin.json")
    suspend fun doLogin(): Response<LoginResponse>
}