package com.example.mainpage

sealed class MainPageState()

object MainProgress : MainPageState()
class MainError(val errorCode: Int): MainPageState()
object MainSuccess: MainPageState()