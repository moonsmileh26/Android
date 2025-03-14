package com.example.android.domain

import com.example.android.quotes.data.QuotesRepository
import com.example.android.quotes.domain.GetQuotesUseCase
import com.example.android.quotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: QuotesRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(repository)
    }

    @Test
    fun `Given the repository When the api returns an empty list Then return the quotes from the database`() =
        runBlocking {
            coEvery { repository.getAllQuotesFromApi() } returns emptyList()
            getQuotesUseCase()
            coVerify(exactly = 1) { repository.getAllQuotesFromDatabase() }
            coVerify(exactly = 0) { repository.clearQuotes() }
            coVerify(exactly = 0) { repository.insertQuotes(any()) }
        }

    @Test
    fun `Given the repository When the api returns a list Then return the quotes from the api`() =
        runBlocking {
            val quotes = listOf(Quote("quote", "author"))
            coEvery { repository.getAllQuotesFromApi() } returns quotes
            val response = getQuotesUseCase()

            coVerify(exactly = 1) { repository.clearQuotes() }
            coVerify(exactly = 1) { repository.insertQuotes(any()) }
            coVerify(exactly = 0) { repository.getAllQuotesFromDatabase() }
            assert(quotes == response)
        }
}