package com.example.android.login.ui

data class LoginState(
    val isLoginSuccess: Boolean = false,
    val isLoginError: Boolean = false,
    val isLoading: Boolean = false
)