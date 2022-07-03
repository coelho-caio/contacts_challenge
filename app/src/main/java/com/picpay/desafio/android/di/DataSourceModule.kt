package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.datasource.PicPayRepository
import com.picpay.desafio.android.data.datasource.PicPayRepositoryImpl
import com.picpay.desafio.android.data.local.AppDataBase
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.remote.service.PicPayService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

val dataSourceModule = module {

    fun provideUserDao(dataBase: AppDataBase): UserDao {
        return dataBase.userDao()
    }

    factory<PicPayService> { providePicPayService(get()) }

    single {
        AppDataBase.createDatabase(androidApplication())
    }

    single {
        provideUserDao(dataBase = get())
    }
    single<PicPayRepository> {
        PicPayRepositoryImpl(service = get(), userDao = get())
    }
}

private fun providePicPayService(retrofit: Retrofit) = retrofit.create(PicPayService::class.java)