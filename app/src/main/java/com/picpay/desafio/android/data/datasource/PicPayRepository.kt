package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User

interface PicPayRepository {
    suspend operator fun invoke(): Result<List<User>>
}