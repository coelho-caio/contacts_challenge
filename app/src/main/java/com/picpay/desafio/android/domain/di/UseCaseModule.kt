package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import com.picpay.desafio.android.domain.usecase.PicPayUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<PicPayUseCase> { PicPayUseCaseImpl(get()) }
}