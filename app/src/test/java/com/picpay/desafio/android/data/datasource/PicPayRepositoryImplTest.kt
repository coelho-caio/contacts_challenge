package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.mapper.toEntity
import com.picpay.desafio.android.data.remote.model.UserResponse
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PicPayRepositoryImplTest {

    private val service = mockk<PicPayService>()

    private val userDao = mockk<UserDao>()
    lateinit var repository: PicPayRepository

    @Before
    fun setup() {
        repository = PicPayRepositoryImpl(service = service, userDao = userDao)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `fetch users successfully`() = runTest {
        val response = UserResponse(id = 1, name = "test", img = "test", username = "test")
        val expectedValue =
            listOf(response)

        coEvery { service.getUsers() } returns expectedValue
        coEvery { userDao.insertUsers(response.toEntity()) } returns Unit
        coEvery { userDao.getAllUsers() } returns listOf(response.toEntity())


        val actualValue = repository() as Result.Success

        Assert.assertEquals(expectedValue.first().id, actualValue.data.first().id)

    }

}