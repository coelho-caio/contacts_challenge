package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.datasource.PicPayRepository
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User

class PicPayUseCaseImpl(private val repository: PicPayRepository) : PicPayUseCase {
    override suspend fun getUser(): Result<List<User>> =
        repository()
}