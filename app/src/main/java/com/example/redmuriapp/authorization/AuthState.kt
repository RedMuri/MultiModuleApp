package com.example.redmuriapp.authorization

sealed class AuthState()

object Progress : AuthState()
class Error(val errorCode: Int): AuthState()
class Success(val firstName: String): AuthState()
