package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.di.dataSourceModule
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.domain.di.useCaseModule
import com.picpay.desafio.android.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(dataSourceModule, useCaseModule, viewModelModule, networkModule)

            )
        }
    }
}