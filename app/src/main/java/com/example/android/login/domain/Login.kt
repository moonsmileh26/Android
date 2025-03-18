package com.example.android.login.domain

import com.example.android.login.data.LoginResponse
import com.example.android.login.data.User

data class Login(
    val success: Boolean,
    val message: String,
    val user: User
)

data class User(
    val email: String
)

fun LoginResponse.toDomain(): Login = Login(success, message, user)