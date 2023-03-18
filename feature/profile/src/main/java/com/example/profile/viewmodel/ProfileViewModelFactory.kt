package com.example.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.use_cases.EditUserImageUseCase
import com.example.domain.use_cases.GetUserUseCase
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val editUserImageUseCase: EditUserImageUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == ProfileViewModel::class.java)
            return ProfileViewModel(getUserUseCase, editUserImageUseCase) as T
        throw RuntimeException("Unknown view model class $modelClass")
    }
}