package com.example.data.repository

import com.example.data.mappers.ItemsMapper
import com.example.data.model.FlashSaleItem
import com.example.data.model.LatestItem
import com.example.network.retrofit.ItemsApiService
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsMapper: ItemsMapper,
    private val itemsApiService: ItemsApiService
) : ItemsRepository {

    override suspend fun getLatestItems(): List<LatestItem> {
        return itemsApiService.getLatestItemsContainer().latestItemsList.map {
            itemsMapper.latestItemDtoToEntity(it)
        }
    }

    override suspend fun getFlashSaleItems(): List<FlashSaleItem> {
        return itemsApiService.getFlashSaleItemsContainer().flashSaleItemsList.map {
            itemsMapper.flashSaleItemDtoToEntity(it)
        }
    }
}