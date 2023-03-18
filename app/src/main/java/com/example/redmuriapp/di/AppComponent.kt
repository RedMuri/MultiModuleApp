package com.example.redmuriapp.di

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.data.di.DataModule
import com.example.db.di.DbModule
import com.example.network.di.NetworkModule
import com.example.profile.di.ProfileComponent
import com.example.profile.fragment.ProfileFragment
import com.example.auth.fragment.LogInFragment
import com.example.mainpage.fragment.MainPageFragment
import com.example.auth.fragment.SignInFragment
import com.example.mainpage.di.MainPageComponent
import com.example.redmuriapp.RedMuriApp
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DbModule::class, NetworkModule::class, DataModule::class]
)
interface AppComponent : ProfileComponent, AuthComponent, MainPageComponent {

    fun inject(application: RedMuriApp)

    override fun inject(logInFragment: LogInFragment)

    override fun inject(signInFragment: SignInFragment)

    override fun inject(mainPageFragment: MainPageFragment)

    override fun inject(profileFragment: ProfileFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}