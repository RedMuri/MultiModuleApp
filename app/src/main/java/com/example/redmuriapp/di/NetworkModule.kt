package com.example.redmuriapp.di

import com.example.redmuriapp.data.network.ItemsRepositoryImpl
import com.example.redmuriapp.data.network.retrofit.ItemsApiFactory
import com.example.redmuriapp.data.network.retrofit.ItemsApiService
import com.example.redmuriapp.domain.repositories.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NetworkModule {

    @Binds
    fun bindRepository(impl: ItemsRepositoryImpl): ItemsRepository

    companion object {

        @Provides
        fun provideApiService(): ItemsApiService {
            return ItemsApiFactory.itemsApiService
        }
    }
}