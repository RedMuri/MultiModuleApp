package com.example.redmuriapp.data.network.models

import com.google.gson.annotations.SerializedName


data class LatestItemDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("image_url")
    val imageUrl: String,
)
