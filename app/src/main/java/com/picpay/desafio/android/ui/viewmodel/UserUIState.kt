package com.picpay.desafio.android.ui.viewmodel

import com.picpay.desafio.android.domain.model.User

data class UserUIState(
    val isLoading: Boolean = false,
    val list: List<User> = emptyList(),
    val uiError: List<PicPayUiError> = emptyList()
)
