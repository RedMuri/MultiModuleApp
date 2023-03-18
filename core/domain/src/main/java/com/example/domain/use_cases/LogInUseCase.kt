package com.example.domain.use_cases

import com.example.data.repository.UsersRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(firstName: String, password: String, callback: () -> Unit) =
        usersRepository.logIn(firstName, password, callback)
}