package com.example.mainpage.di

import android.app.Application
import com.example.data.di.DataModule
import com.example.db.di.DbModule
import com.example.mainpage.fragment.MainPageFragment
import com.example.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, DbModule::class, NetworkModule::class, MainPageViewModelModule::class]
)
interface MainPageComponent {

    fun inject(mainPageFragment: MainPageFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): MainPageComponent
    }
}

interface MainPageComponentProvider {
    fun getMainPageComponent(): MainPageComponent
}