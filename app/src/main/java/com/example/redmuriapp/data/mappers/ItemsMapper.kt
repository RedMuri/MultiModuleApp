package com.example.redmuriapp.data.mappers

import com.example.redmuriapp.domain.entities.FlashSaleItem
import com.example.redmuriapp.domain.entities.LatestItem
import com.example.redmuriapp.retrofit.FlashSaleItemDto
import com.example.redmuriapp.retrofit.LatestItemDto

class ItemsMapper {

    fun latestItemDtoToEntity(latestItemDto: LatestItemDto) = LatestItem(
        category = latestItemDto.category,
        name = latestItemDto.name,
        price = latestItemDto.price,
        image_url = latestItemDto.image_url,
    )

    fun flashSaleItemDtoToEntity(flashSaleItemDto: FlashSaleItemDto) = FlashSaleItem(
        category = flashSaleItemDto.category,
        name = flashSaleItemDto.name,
        price = flashSaleItemDto.price,
        discount = flashSaleItemDto.discount,
        image_url = flashSaleItemDto.image_url,
    )
}