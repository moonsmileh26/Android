package com.example.android.login.network

import com.example.android.login.data.LoginResponse
import com.example.android.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val apiClient: ApiClient) {
    suspend fun doLogin(): LoginResponse? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.doLogin()
            response.body()
        }
    }
}