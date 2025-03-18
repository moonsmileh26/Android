package com.example.android.login.domain

import com.example.android.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(): Login? {
        return repository.doLogin()
    }
}