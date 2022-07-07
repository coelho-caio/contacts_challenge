package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.Result.Failure
import com.picpay.desafio.android.domain.Result.Success
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PicPayViewModel(private val useCase: PicPayUseCase) : ViewModel() {

    private val _user = MutableStateFlow(UserUIState())
    val user = _user.asStateFlow()

    fun handleInitView() {
        viewModelScope.launch {
            _user.value = _user.value.copy(isLoading = true)
            when (val result = useCase.getUser()) {
                is Success -> {
                    _user.value = _user.value.copy(
                        isLoading = false,
                        list = result.data
                    )
                }
                is Failure -> {
                    _user.value = _user.value.copy(
                        isLoading = false,
                        list = emptyList(),
                        uiError = listOf(PicPayUiError.FailedToFetchInformation)
                    )
                }
            }
        }
    }
}