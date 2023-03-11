package com.example.redmuriapp.db

import android.app.Application
import kotlinx.coroutines.delay

class RepositoryImpl(application: Application) {

    private val authDao = AppDatabase.getInstance(application).authDao()

    @Throws(UserAlreadyExistsException::class)
    suspend fun signIn(user: UserDbModel, callback: () -> Unit) {
        val existingUser = authDao.getUserByFirstName(user.firstName)
        delay(1000)
        if (existingUser == null) {
            authDao.addUser(user)
            callback.invoke()
        } else throw UserAlreadyExistsException()
    }

    @Throws(UserDoesNotExistsException::class, WrongPasswordLogInException::class)
    suspend fun logIn(firstName: String, password: String, callback: () -> Unit) {
        val existingUser = authDao.getUserByFirstName(firstName)
        delay(1000)
        if (existingUser != null) {
            if (existingUser.password==password) {
                callback.invoke()
            } else throw WrongPasswordLogInException()
        } else throw UserDoesNotExistsException()
    }
}