package com.example.network.models

import com.google.gson.annotations.SerializedName

data class FlashSaleItemsContainerDto(
    @SerializedName("flash_sale")
    val flashSaleItemsList: List<FlashSaleItemDto>
)