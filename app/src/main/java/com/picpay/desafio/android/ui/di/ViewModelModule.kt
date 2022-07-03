package com.picpay.desafio.android.ui.di

import com.picpay.desafio.android.ui.viewmodel.PicPayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PicPayViewModel(get()) }
}