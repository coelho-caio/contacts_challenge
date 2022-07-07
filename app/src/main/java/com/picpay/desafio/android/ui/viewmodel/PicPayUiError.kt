package com.picpay.desafio.android.ui.viewmodel

sealed class PicPayUiError{
    object FailedToFetchInformation: PicPayUiError()
}
