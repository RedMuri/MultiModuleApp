package com.example.redmuriapp.domain.repositories

import com.example.redmuriapp.domain.entities.FlashSaleItem
import com.example.redmuriapp.domain.entities.LatestItem

interface ItemsRepository {

    suspend fun getLatestItems(): List<LatestItem>

    suspend fun getFlashSaleItems(): List<FlashSaleItem>
}