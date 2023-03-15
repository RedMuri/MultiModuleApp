package com.example.redmuriapp.domain.repositories

import com.example.redmuriapp.domain.entities.User

interface UsersRepository {

    suspend fun signIn(user: User, callback: () -> Unit)

    suspend fun logIn(firstName: String, password: String, callback: () -> Unit)

    suspend fun getUser(firstName: String): User
}