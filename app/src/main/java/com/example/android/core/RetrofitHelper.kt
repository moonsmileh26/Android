package com.example.android.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/moonsmileh26/618a6bc8f53831bcbd22d76013158809/raw/2e8ac6dfa6930bda734a03cb441451c3d834d88e/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}