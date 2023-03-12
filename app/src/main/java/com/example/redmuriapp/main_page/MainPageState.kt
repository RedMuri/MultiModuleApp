package com.example.redmuriapp.main_page

sealed class MainPageState()

object Progress : MainPageState()
class Error(val errorCode: Int): MainPageState()
object Success: MainPageState()