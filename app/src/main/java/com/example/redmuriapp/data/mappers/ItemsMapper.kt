package com.example.redmuriapp.data.mappers

import com.example.redmuriapp.domain.entities.FlashSaleItem
import com.example.redmuriapp.domain.entities.LatestItem
import com.example.redmuriapp.data.network.models.FlashSaleItemDto
import com.example.redmuriapp.data.network.models.LatestItemDto
import javax.inject.Inject

class ItemsMapper @Inject constructor() {

    fun latestItemDtoToEntity(latestItemDto: LatestItemDto) = LatestItem(
        category = latestItemDto.category,
        name = latestItemDto.name,
        price = latestItemDto.price,
        image_url = latestItemDto.imageUrl,
    )

    fun flashSaleItemDtoToEntity(flashSaleItemDto: FlashSaleItemDto) = FlashSaleItem(
        category = flashSaleItemDto.category,
        name = flashSaleItemDto.name,
        price = flashSaleItemDto.price,
        discount = flashSaleItemDto.discount,
        image_url = flashSaleItemDto.imageUrl,
    )
}