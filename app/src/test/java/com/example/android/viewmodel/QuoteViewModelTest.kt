package com.example.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.quotes.domain.GetQuotesUseCase
import com.example.android.quotes.domain.GetRandomQuoteUseCase
import com.example.android.quotes.domain.model.Quote
import com.example.android.quotes.viewmodel.QuoteViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuoteViewModelTest {
    private lateinit var viewModel: QuoteViewModel

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given the getQuotesUseCase When viewModel is created at the first time Then get all quotes and set the first value`() =
        runTest {
            val quotes = listOf(Quote("quote", "author"))
            coEvery { getQuotesUseCase() } returns quotes

            assert(viewModel.quote.value == null)

            viewModel.onCreate()

            assert(viewModel.quote.value == quotes.first())
        }

    @Test
    fun `Given the getQuotesUseCase When getRandomQuoteUseCase is called Then set a quote on the livedata`() = runTest {
        val quote = Quote("quote", "author")
        coEvery { getRandomQuoteUseCase() } returns quote
        viewModel.getRandomQuote()
        assert(viewModel.quote.value == quote)
    }

    @Test
    fun `Given the getQuotesUseCase When getRandomQuoteUseCase is called and the list is empty Then return null`() = runTest {
        coEvery { getRandomQuoteUseCase() } returns null
        viewModel.getRandomQuote()
        assert(viewModel.quote.value == null)
    }

    @Test
    fun `Given the getQuotesUseCase When getRandomQuoteUseCase is called and it is null Then return the last quote`() = runTest {
        val quote = Quote("quote", "author")
        viewModel.quote.value = quote
        coEvery { getRandomQuoteUseCase() } returns null
        viewModel.getRandomQuote()
        assert(viewModel.quote.value == quote)
    }
}