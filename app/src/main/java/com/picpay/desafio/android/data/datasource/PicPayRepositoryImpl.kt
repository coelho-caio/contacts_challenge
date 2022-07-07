package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.data.mapper.toEntity
import com.picpay.desafio.android.data.mapper.toResponse
import com.picpay.desafio.android.data.remote.model.UserResponse
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PicPayRepositoryImpl(
    private val service: PicPayService,
    private val userDao: UserDao
) : PicPayRepository {
    override suspend fun invoke(): Result<List<User>> {
        return withContext(Dispatchers.IO)
        {
            try {
                val usersResponse = service.getUsers()
                saveUserInDb(usersResponse)
                val domainResult = getUserDb().map { it.toDomain() }
                Result.Success(domainResult)
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }

        private suspend fun saveUserInDb(list: List<UserResponse>) {
        list.forEach {
            userDao.insertUsers(it.toEntity())
        }

    }

    private fun getUserDb(): List<UserResponse> {
        return userDao.getAllUsers().map { it.toResponse() }
    }
}


