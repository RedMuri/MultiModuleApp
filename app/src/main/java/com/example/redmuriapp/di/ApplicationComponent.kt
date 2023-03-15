package com.example.redmuriapp.di

import android.app.Application
import com.example.redmuriapp.ui.RedMuriApp
import com.example.redmuriapp.ui.fragments.LogInFragment
import com.example.redmuriapp.ui.fragments.MainPageFragment
import com.example.redmuriapp.ui.fragments.SignInFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DbModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: RedMuriApp)

    fun inject(logInFragment: LogInFragment)

    fun inject(signInFragment: SignInFragment)

    fun inject(mainPageFragment: MainPageFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}