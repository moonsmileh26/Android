package com.example.android.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.login.domain.LoginUseCase
import com.example.android.login.ui.LoginState
import com.example.android.login.ui.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginViewState = MutableStateFlow(LoginViewState())
    val loginViewState: StateFlow<LoginViewState> = _loginViewState

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()


    fun onLoginChanged(email: String, password: String) {
        _loginViewState.value = _loginViewState.value.copy(
            email = email,
            password = password,
            loginEnable = isValidEmail(email) && isValidPassword(password)
        )
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    fun onDoLogin() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = loginUseCase()
            val isSuccess = result?.user?.email?.equals(_loginViewState.value.email)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoginSuccess = isSuccess ?: false,
                isLoginError = !isSuccess!!
            )

        }
    }

}