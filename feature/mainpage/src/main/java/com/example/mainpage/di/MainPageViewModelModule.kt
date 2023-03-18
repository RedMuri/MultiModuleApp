package com.example.mainpage.di

import com.example.domain.use_cases.GetFlashSaleItemsUseCase
import com.example.domain.use_cases.GetLatestItemsUseCase
import com.example.domain.use_cases.GetUserUseCase
import com.example.mainpage.viewmodel.MainPageViewModel
import dagger.Module
import dagger.Provides

@Module
object MainPageViewModelModule {

    @Provides
    fun provideMainPageViewModel(
        getUserUseCase: GetUserUseCase,
        getLatestItemsUseCase: GetLatestItemsUseCase,
        getFlashSaleItemsUseCase: GetFlashSaleItemsUseCase
    ): MainPageViewModel {
        return MainPageViewModel(getUserUseCase, getLatestItemsUseCase, getFlashSaleItemsUseCase)
    }
}