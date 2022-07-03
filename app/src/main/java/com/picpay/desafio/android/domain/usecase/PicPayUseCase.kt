package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User

interface PicPayUseCase {
    suspend fun getUser(): Result<List<User>>
}