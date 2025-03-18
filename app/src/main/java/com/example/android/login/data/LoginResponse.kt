package com.example.android.login.data

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val user: User
)

data class User(
    val email: String
)