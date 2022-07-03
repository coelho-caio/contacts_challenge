package com.picpay.desafio.android.data.remote.service

import com.picpay.desafio.android.data.remote.model.UserResponse
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsers(): List<UserResponse>
}