package com.example.redmuriapp.main_page

sealed class MainPageState()

object MainProgress : MainPageState()
class MainError(val errorCode: Int): MainPageState()
object MainSuccess: MainPageState()