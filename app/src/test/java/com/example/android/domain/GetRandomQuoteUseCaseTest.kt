package com.example.android.domain

import com.example.android.quotes.data.QuotesRepository
import com.example.android.quotes.domain.GetRandomQuoteUseCase
import com.example.android.quotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var quotesRepository: QuotesRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quotesRepository)
    }

    @Test
    fun `Given the repository When the database returns an empty list Then return null`() =
        runBlocking {
            coEvery { quotesRepository.getAllQuotesFromDatabase() } returns emptyList()

            val response = getRandomQuoteUseCase()

            assert(response == null)
        }

    @Test
    fun `Given the repository When the database returns a list Then return a random quote`() =
        runBlocking {

            val quotes = listOf(Quote("quote", "author"))
            coEvery { quotesRepository.getAllQuotesFromDatabase() } returns quotes

            val response = getRandomQuoteUseCase()

            assert(response == quotes.first())
        }
}