package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.UsersRepository

class LogInUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(firstName: String, password: String, callback: () -> Unit) =
        usersRepository.logIn(firstName, password, callback)
}