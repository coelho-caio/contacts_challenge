package com.picpay.desafio.android.ui.viewmodel

import com.picpay.desafio.android.domain.model.User

data class UserUIState(
    val list: List<User> = emptyList()
)
