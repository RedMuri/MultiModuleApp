package com.example.network.di

import com.example.network.retrofit.ItemsApiFactory
import com.example.network.retrofit.ItemsApiService
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {

    @Provides
    fun provideApiService(): ItemsApiService {
        return ItemsApiFactory.itemsApiService
    }
}