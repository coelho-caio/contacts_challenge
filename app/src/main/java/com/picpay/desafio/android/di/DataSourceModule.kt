package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.datasource.PicPayRepository
import com.picpay.desafio.android.data.datasource.PicPayRepositoryImpl
import com.picpay.desafio.android.data.remote.service.PicPayService
import org.koin.dsl.module
import retrofit2.Retrofit

val dataSourceModule = module {

    factory<PicPayService> { providePicPayService(get()) }

    single<PicPayRepository> {
        PicPayRepositoryImpl(service = get(), userDao = get())
    }
}

private fun providePicPayService(retrofit: Retrofit) = retrofit.create(PicPayService::class.java)