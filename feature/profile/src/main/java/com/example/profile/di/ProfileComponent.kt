package com.example.profile.di

import android.app.Application
import com.example.data.di.DataModule
import com.example.db.di.DbModule
import com.example.network.di.NetworkModule
import com.example.profile.fragment.ProfileFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, DbModule::class, NetworkModule::class, ProfileViewModelModule::class]
)
interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ProfileComponent
    }
}

interface ProfileComponentProvider {
    fun getProfileComponent(): ProfileComponent
}