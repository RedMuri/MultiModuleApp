package com.example.redmuriapp.data.network.models

import com.google.gson.annotations.SerializedName

data class FlashSaleItemDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("image_url")
    val imageUrl: String,
)
