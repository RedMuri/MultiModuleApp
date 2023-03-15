package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.entities.User
import com.example.redmuriapp.domain.repositories.UsersRepository

class SignInUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(user: User, callback: () -> Unit) =
        usersRepository.signIn(user, callback)
}