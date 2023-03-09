package com.example.redmuriapp.signin

sealed class SignInState()

object Progress : SignInState()
class Error(val errorCode: Int): SignInState()
object Success: SignInState()
