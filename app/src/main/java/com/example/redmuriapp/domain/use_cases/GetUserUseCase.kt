package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.UsersRepository

class GetUserUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(firstName: String) =
        usersRepository.getUser(firstName)
}