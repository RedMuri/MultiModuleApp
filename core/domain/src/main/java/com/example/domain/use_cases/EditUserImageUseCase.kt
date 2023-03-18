package com.example.domain.use_cases

import com.example.data.repository.UsersRepository
import javax.inject.Inject

class EditUserImageUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(firstName: String, imageUrl: String) =
        usersRepository.editUserImage(firstName, imageUrl)
}