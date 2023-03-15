package com.example.redmuriapp.domain.use_cases

import com.example.redmuriapp.domain.repositories.ItemsRepository

class GetFlashSaleItemsUseCase(private val itemsRepository: ItemsRepository) {
    suspend operator fun invoke() = itemsRepository.getFlashSaleItems()
}