package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.UsersRepository
import javax.inject.Inject

class EditUserImageUseCase @Inject constructor(private val usersRepository: UsersRepository)  {
    suspend operator fun invoke(firstName: String, imageUrl: String) =
        usersRepository.editUserImage(firstName, imageUrl)
}