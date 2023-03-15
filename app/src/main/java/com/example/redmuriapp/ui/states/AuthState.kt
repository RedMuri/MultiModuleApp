package com.example.redmuriapp.ui.states

sealed class AuthState()

object AuthProgress : AuthState()
class AuthError(val errorCode: Int): AuthState()
class AuthSuccess(val firstName: String): AuthState()
