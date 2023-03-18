package com.example.mainpage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.use_cases.GetFlashSaleItemsUseCase
import com.example.domain.use_cases.GetLatestItemsUseCase
import com.example.domain.use_cases.GetUserUseCase
import javax.inject.Inject

class MainPageViewModelFactory @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getLatestItemsUseCase: GetLatestItemsUseCase,
    private val getFlashSaleItemsUseCase: GetFlashSaleItemsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MainPageViewModel::class.java)
            return MainPageViewModel(getUserUseCase, getLatestItemsUseCase, getFlashSaleItemsUseCase) as T
        throw RuntimeException("Unknown view model class $modelClass")
    }
}