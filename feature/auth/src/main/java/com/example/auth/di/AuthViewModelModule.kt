package com.example.auth.di

import com.example.auth.viewmodel.LogInViewModel
import com.example.auth.viewmodel.SignInViewModel
import com.example.domain.use_cases.LogInUseCase
import com.example.domain.use_cases.SignInUseCase
import dagger.Module
import dagger.Provides

@Module
object AuthViewModelModule {


    @Provides
    fun provideSignInViewModel(signInUseCase: SignInUseCase): SignInViewModel {
        return SignInViewModel(signInUseCase)
    }

    @Provides
    fun provideLogInViewModel(logInUseCase: LogInUseCase): LogInViewModel {
        return LogInViewModel(logInUseCase)
    }
}