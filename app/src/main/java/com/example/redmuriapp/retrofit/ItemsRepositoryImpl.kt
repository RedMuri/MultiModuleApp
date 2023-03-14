package com.example.redmuriapp.retrofit

import kotlinx.coroutines.delay

class ItemsRepositoryImpl() {

    private val itemsApiService = ItemsApiFactory.itemsApiService

    suspend fun getLatestItems(): List<LatestItemDto> {
        return itemsApiService.getLatestItems().latest
    }

    suspend fun getFlashSaleItems(): List<FlashSaleItemDto> {
        return itemsApiService.getFlashSaleItems().flash_sale
    }
}