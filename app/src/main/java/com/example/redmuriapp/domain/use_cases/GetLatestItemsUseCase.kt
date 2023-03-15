package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.ItemsRepository
import javax.inject.Inject

class GetLatestItemsUseCase @Inject constructor(private val itemsRepository: ItemsRepository)  {
    suspend operator fun invoke() = itemsRepository.getLatestItems()
}