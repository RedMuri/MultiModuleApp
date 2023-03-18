package com.example.domain.use_cases

import com.example.data.repository.UsersRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val usersRepository: UsersRepository)  {
    suspend operator fun invoke(firstName: String) =
        usersRepository.getUser(firstName)
}