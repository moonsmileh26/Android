package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.model.QuoteModel
import com.example.android.model.QuoteProvider

class QuoteViewModel: ViewModel() {

    val quote = MutableLiveData<QuoteModel>()

    fun getRandomQuote(){
        val currentQuote = QuoteProvider.getQuote()
        quote.postValue(currentQuote)
    }
}