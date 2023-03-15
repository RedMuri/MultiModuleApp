package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.ItemsRepository

class GetLatestItemsUseCase(private val itemsRepository: ItemsRepository) {
    suspend operator fun invoke() = itemsRepository.getLatestItems()
}