package com.example.redmuriapp.data.network.models

import com.google.gson.annotations.SerializedName

data class LatestItemsContainerDto(
    @SerializedName("latest")
    val latestItemsList: List<LatestItemDto>
)