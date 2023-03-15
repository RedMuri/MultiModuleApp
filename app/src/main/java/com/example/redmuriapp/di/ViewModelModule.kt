package com.example.redmuriapp.di

import androidx.lifecycle.ViewModel
import com.example.redmuriapp.ui.view_models.LogInViewModel
import com.example.redmuriapp.ui.view_models.MainPageViewModel
import com.example.redmuriapp.ui.view_models.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun bindLogInViewModel(viewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainPageViewModel::class)
    fun bindMainPageViewModel(viewModel: MainPageViewModel): ViewModel

}