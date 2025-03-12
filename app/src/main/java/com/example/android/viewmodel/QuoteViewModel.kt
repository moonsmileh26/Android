package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.data.model.QuoteModel
import com.example.android.domain.GetQuotesUseCase
import com.example.android.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    var getQuote = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()


    val quote = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun getRandomQuote() {
        isLoading.postValue(true)
        val randomQuote = getRandomQuoteUseCase()
        randomQuote?.let {
            quote.postValue(it)
        }
        isLoading.postValue(false)
    }

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuote()
            if (result.isNotEmpty()) {
                quote.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}