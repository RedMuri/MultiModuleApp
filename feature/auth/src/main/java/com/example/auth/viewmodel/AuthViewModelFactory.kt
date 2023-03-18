package com.example.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.use_cases.*
import javax.inject.Inject

class AuthViewModelFactory @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val logInUseCase: LogInUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == SignInViewModel::class.java)
            return SignInViewModel(signInUseCase) as T
        else if (modelClass == LogInViewModel::class.java)
            return LogInViewModel(logInUseCase) as T
        throw RuntimeException("Unknown view model class $modelClass")
    }
}