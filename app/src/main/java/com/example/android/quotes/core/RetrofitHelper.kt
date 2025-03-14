package com.example.android.quotes.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/moonsmileh26/618a6bc8f53831bcbd22d76013158809/raw/fd8684f2e0cb25c6c898e375c6dacc4b85efdcc6/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}