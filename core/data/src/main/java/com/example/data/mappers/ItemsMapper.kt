package com.example.data.mappers

import com.example.data.model.FlashSaleItem
import com.example.data.model.LatestItem
import javax.inject.Inject

class ItemsMapper @Inject constructor() {

    fun latestItemDtoToEntity(latestItemDto: com.example.network.models.LatestItemDto) =
        LatestItem(
            category = latestItemDto.category,
            name = latestItemDto.name,
            price = latestItemDto.price,
            image_url = latestItemDto.imageUrl,
        )

    fun flashSaleItemDtoToEntity(flashSaleItemDto: com.example.network.models.FlashSaleItemDto) =
        FlashSaleItem(
            category = flashSaleItemDto.category,
            name = flashSaleItemDto.name,
            price = flashSaleItemDto.price,
            discount = flashSaleItemDto.discount,
            image_url = flashSaleItemDto.imageUrl,
        )
}