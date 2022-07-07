package com.picpay.desafio.android.ui.viewmodel

import com.picpay.desafio.android.MainDispatcherRule
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.*

class PicPayViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val useCase = mockk<PicPayUseCase>()

    lateinit var viewModel: PicPayViewModel

    @Before
    fun setup() {
        viewModel = PicPayViewModel(useCase = useCase)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `test fetch users successfully`()= runTest{
        val expectedValue =
            Result.Success(listOf(User(id = 1, name = "test", img = "test", username = "test")))
        coEvery { useCase.getUser() } returns expectedValue

        viewModel.handleInitView()

        advanceUntilIdle()

        Assert.assertEquals(expectedValue.data.first(), viewModel.user.value.list.first())

    }

    @Test
    fun `when useCase return fail it should returns error`()= runTest{
        val expectedResult = PicPayUiError.FailedToFetchInformation
        val response =
            Result.Failure(Exception())
        coEvery { useCase.getUser() } returns response

        viewModel.handleInitView()

        advanceUntilIdle()

        Assert.assertEquals(expectedResult, viewModel.user.value.uiError.first())

    }

}