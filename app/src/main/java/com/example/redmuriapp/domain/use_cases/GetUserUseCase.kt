package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.ItemsRepository
import com.example.redmuriapp.domain.repositories.UsersRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val usersRepository: UsersRepository)  {
    suspend operator fun invoke(firstName: String) =
        usersRepository.getUser(firstName)
}