package com.example.redmuriapp.data.db.repositories

import android.app.Application
import com.example.redmuriapp.data.db.AppDatabase
import com.example.redmuriapp.data.db.UsersDao
import com.example.redmuriapp.data.mappers.UsersMapper
import com.example.redmuriapp.domain.exceptions.UserAlreadyExistsException
import com.example.redmuriapp.domain.exceptions.UserDoesNotExistsException
import com.example.redmuriapp.domain.exceptions.UserNotFoundException
import com.example.redmuriapp.domain.exceptions.WrongPasswordLogInException
import com.example.redmuriapp.domain.entities.User
import com.example.redmuriapp.domain.repositories.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val usersMapper: UsersMapper
) : UsersRepository {

    @Throws(UserAlreadyExistsException::class)
    override suspend fun signIn(user: User, callback: () -> Unit) {
        val existingUser = usersDao.getUserByFirstName(user.firstName)
        if (existingUser == null) {
            usersDao.addUser(usersMapper.userEntityToDbModel(user))
            callback.invoke()
        } else throw UserAlreadyExistsException()
    }

    @Throws(UserDoesNotExistsException::class, WrongPasswordLogInException::class)
    override suspend fun logIn(firstName: String, password: String, callback: () -> Unit) {
        val existingUser = usersDao.getUserByFirstName(firstName)
        if (existingUser != null) {
            if (existingUser.password == password) {
                callback.invoke()
            } else throw WrongPasswordLogInException()
        } else throw UserDoesNotExistsException()
    }

    @Throws(UserNotFoundException::class)
    override suspend fun getUser(firstName: String): User {
        val userDbModel = usersDao.getUserByFirstName(firstName)
        if (userDbModel != null) {
            return usersMapper.userDbModelToEntity(userDbModel)
        } else throw UserNotFoundException()
    }
}