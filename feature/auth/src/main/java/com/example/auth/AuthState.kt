package com.example.auth

sealed class AuthState()

object AuthProgress : AuthState()
class AuthError(val errorCode: Int): AuthState()
class AuthSuccess(val firstName: String): AuthState()
