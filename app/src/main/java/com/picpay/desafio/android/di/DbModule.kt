package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.data.local.AppDataBase
import com.picpay.desafio.android.data.local.UserDao
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val dbModule: Module = module {
    single {
        provideDb(androidApplication())
    }

    single {
        provideUserDao(dataBase = get())
    }
}

fun provideDb(application: Application): AppDataBase =
    Room.databaseBuilder(
        application.applicationContext,
        AppDataBase::class.java, "my_db"
    ).build()


fun provideUserDao(dataBase: AppDataBase): UserDao {
    return dataBase.userDao()
}