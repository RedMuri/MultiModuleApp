package com.example.redmuriapp

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.auth.di.AuthComponentProvider
import com.example.mainpage.di.MainPageComponent
import com.example.mainpage.di.MainPageComponentProvider
import com.example.profile.di.ProfileComponent
import com.example.profile.di.ProfileComponentProvider
import com.example.redmuriapp.di.DaggerAppComponent

class RedMuriApp : Application(), ProfileComponentProvider, AuthComponentProvider,
    MainPageComponentProvider {

    val component by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getProfileComponent(): ProfileComponent {
        return component
    }

    override fun getAuthComponent(): AuthComponent {
        return component
    }

    override fun getMainPageComponent(): MainPageComponent {
        return component
    }

}