package com.example.data.repository

import com.example.data.model.User

interface UsersRepository {

    suspend fun signIn(user: User, callback: () -> Unit)

    suspend fun logIn(firstName: String, password: String, callback: () -> Unit)

    suspend fun getUser(firstName: String): User

    suspend fun editUserImage(firstName: String, imageUrl: String)
}