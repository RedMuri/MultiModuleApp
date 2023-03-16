package com.example.redmuriapp.di

import android.app.Application
import com.example.redmuriapp.data.db.AppDatabase
import com.example.redmuriapp.data.db.UsersDao
import com.example.redmuriapp.data.db.repositories.UsersRepositoryImpl
import com.example.redmuriapp.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DbModule {

    @Binds
    fun bindRepository(impl: UsersRepositoryImpl): UsersRepository

    companion object {

        @Provides
        fun provideUsersDao(application: Application): UsersDao {
            return AppDatabase.getInstance(application).usersDao()
        }
    }
}