package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.data.model.QuoteModel
import com.example.android.domain.GetQuotesUseCase
import com.example.android.domain.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

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
            val result = getQuotesUseCase()
            if (result.isNotEmpty()) {
                quote.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}