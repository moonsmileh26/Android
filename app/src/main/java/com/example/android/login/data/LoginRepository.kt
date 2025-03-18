package com.example.android.login.data

import com.example.android.login.domain.Login
import com.example.android.login.domain.toDomain
import com.example.android.login.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: LoginService
) {
    suspend fun doLogin(): Login? {
        val response = apiService.doLogin()?.toDomain()
        return response
    }
}