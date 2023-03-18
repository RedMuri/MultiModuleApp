package com.example.profile.di

import com.example.domain.use_cases.EditUserImageUseCase
import com.example.domain.use_cases.GetUserUseCase
import com.example.profile.viewmodel.ProfileViewModel
import dagger.Module
import dagger.Provides

@Module
object ProfileViewModelModule {

    @Provides
    fun provideProfileViewModel(
        getUserUseCase: GetUserUseCase,
        editUserImageUseCase: EditUserImageUseCase
    ): ProfileViewModel {
        return ProfileViewModel(getUserUseCase,editUserImageUseCase)
    }
}