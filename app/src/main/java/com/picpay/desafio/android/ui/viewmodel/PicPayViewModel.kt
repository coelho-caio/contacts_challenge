package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.Result.Failure
import com.picpay.desafio.android.domain.Result.Success
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PicPayViewModel(private val useCase: PicPayUseCase) : ViewModel() {

    private val _user = MutableStateFlow(UserUIState())
    val user = _user

    fun handleInitView() {
        viewModelScope.launch {
            when (val result = useCase.getUser()) {
                is Success -> {
                    _user.value = _user.value.copy(
                        list = result.data
                    )
                }
                is Failure -> {

                }
            }
        }
    }
}