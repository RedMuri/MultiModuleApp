package com.example.redmuriapp.data.db

import android.app.Application
import com.example.redmuriapp.data.mappers.UsersMapper
import com.example.redmuriapp.domain.exceptions.UserAlreadyExistsException
import com.example.redmuriapp.domain.exceptions.UserDoesNotExistsException
import com.example.redmuriapp.domain.exceptions.UserNotFoundException
import com.example.redmuriapp.domain.exceptions.WrongPasswordLogInException
import com.example.redmuriapp.domain.entities.User
import com.example.redmuriapp.domain.repositories.UsersRepository

class UsersRepositoryImpl(application: Application): UsersRepository{

    private val authDao = AppDatabase.getInstance(application).usersDao()
    private val usersMapper = UsersMapper()

    @Throws(UserAlreadyExistsException::class)
    override suspend fun signIn(user: User, callback: () -> Unit) {
        val existingUser = authDao.getUserByFirstName(user.firstName)
        if (existingUser == null) {
            authDao.addUser(usersMapper.userEntityToDbModel(user))
            callback.invoke()
        } else throw UserAlreadyExistsException()
    }

    @Throws(UserDoesNotExistsException::class, WrongPasswordLogInException::class)
    override suspend fun logIn(firstName: String, password: String, callback: () -> Unit) {
        val existingUser = authDao.getUserByFirstName(firstName)
        if (existingUser != null) {
            if (existingUser.password == password) {
                callback.invoke()
            } else throw WrongPasswordLogInException()
        } else throw UserDoesNotExistsException()
    }

    @Throws(UserNotFoundException::class)
    override suspend fun getUser(firstName: String): User {
        val userDbModel = authDao.getUserByFirstName(firstName)
        if (userDbModel != null) {
            return usersMapper.userDbModelToEntity(userDbModel)
        } else throw UserNotFoundException()
    }
}