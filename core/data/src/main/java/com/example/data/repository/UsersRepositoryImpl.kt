package com.example.data.repository

import com.example.data.exceptions.UserAlreadyExistsException
import com.example.data.exceptions.UserDoesNotExistsException
import com.example.data.exceptions.UserNotFoundException
import com.example.data.exceptions.WrongPasswordLogInException
import com.example.data.mappers.UsersMapper
import com.example.data.model.User
import com.example.db.dao.UsersDao
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val usersMapper: UsersMapper,
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

    override suspend fun editUserImage(firstName: String, imageUrl: String) {
        val existingUser = usersDao.getUserByFirstName(firstName)
        if (existingUser != null) {
            usersDao.editUser(existingUser.copy(profileImage = imageUrl))
        }
    }
}