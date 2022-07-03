package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

val networkModule: Module = module {
    single { provideRetrofit() }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}
