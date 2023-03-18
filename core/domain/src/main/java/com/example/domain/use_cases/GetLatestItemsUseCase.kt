package com.example.domain.use_cases

import com.example.data.repository.ItemsRepository
import javax.inject.Inject

class GetLatestItemsUseCase @Inject constructor(private val itemsRepository: ItemsRepository)  {
    suspend operator fun invoke() = itemsRepository.getLatestItems()
}