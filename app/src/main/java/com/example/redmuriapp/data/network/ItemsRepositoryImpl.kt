package com.example.redmuriapp.data.network

import com.example.redmuriapp.data.mappers.ItemsMapper
import com.example.redmuriapp.data.network.retrofit.ItemsApiFactory
import com.example.redmuriapp.data.network.retrofit.ItemsApiService
import com.example.redmuriapp.domain.entities.FlashSaleItem
import com.example.redmuriapp.domain.entities.LatestItem
import com.example.redmuriapp.domain.repositories.ItemsRepository
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