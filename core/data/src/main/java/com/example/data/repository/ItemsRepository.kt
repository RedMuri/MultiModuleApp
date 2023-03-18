package com.example.data.repository

import com.example.data.model.FlashSaleItem
import com.example.data.model.LatestItem

interface ItemsRepository {

    suspend fun getLatestItems(): List<LatestItem>

    suspend fun getFlashSaleItems(): List<FlashSaleItem>
}