package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.datasource.PicPayRepository
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.viewmodel.PicPayViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PicPayUseCaseImplTest {

    lateinit var useCase: PicPayUseCase

    val repository = mockk<PicPayRepository>()

    @Before
    fun setup() {
        useCase = PicPayUseCaseImpl(repository = repository)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `fetch users successfully`() = runTest {
        val expectedValue =
            Result.Success(listOf(User(id = 1, name = "test", img = "test", username = "test")))

        coEvery { repository.invoke() } returns expectedValue

        val actualValue = useCase.getUser()

        Assert.assertEquals(expectedValue, actualValue)

    }
}