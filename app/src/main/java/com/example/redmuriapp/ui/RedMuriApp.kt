package com.example.redmuriapp.ui

import android.app.Application
import com.example.redmuriapp.di.DaggerApplicationComponent

class RedMuriApp: Application() {

    val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}