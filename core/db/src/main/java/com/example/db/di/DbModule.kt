package com.example.db.di

import android.app.Application
import com.example.db.AppDatabase
import com.example.db.dao.UsersDao
import dagger.Module
import dagger.Provides

@Module
class DbModule() {

    @Provides
    fun provideUsersDao(application: Application): UsersDao {
        return AppDatabase.getInstance(application).usersDao()
    }
}