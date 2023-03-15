package com.example.redmuriapp.data.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ItemsApiFactory {
    private const val BASE_URL = "https://run.mocky.io/v3/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val itemsApiService: ItemsApiService = retrofit.create(ItemsApiService::class.java)
}