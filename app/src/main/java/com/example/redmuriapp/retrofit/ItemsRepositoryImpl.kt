package com.example.redmuriapp.retrofit

import com.example.redmuriapp.data.mappers.ItemsMapper
import com.example.redmuriapp.domain.entities.FlashSaleItem
import com.example.redmuriapp.domain.entities.LatestItem
import com.example.redmuriapp.domain.repositories.ItemsRepository

class ItemsRepositoryImpl() : ItemsRepository {

    private val itemsMapper = ItemsMapper()

    private val itemsApiService = ItemsApiFactory.itemsApiService

    override suspend fun getLatestItems(): List<LatestItem> {
        return itemsApiService.getLatestItemsContainer().latest.map {
            itemsMapper.latestItemDtoToEntity(it)
        }
    }

    override suspend fun getFlashSaleItems(): List<FlashSaleItem> {
        return itemsApiService.getFlashSaleItemsContainer().flash_sale.map {
            itemsMapper.flashSaleItemDtoToEntity(it)
        }
    }
}