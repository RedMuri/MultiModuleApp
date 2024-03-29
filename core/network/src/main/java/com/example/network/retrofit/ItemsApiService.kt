package com.example.network.retrofit

import com.example.network.models.FlashSaleItemsContainerDto
import com.example.network.models.LatestItemsContainerDto
import retrofit2.http.GET

interface ItemsApiService {

    @GET(LATEST_ENDPOINT)
    suspend fun getLatestItemsContainer(): LatestItemsContainerDto

    @GET(FLASH_SALE_ENDPOINT)
    suspend fun getFlashSaleItemsContainer(): FlashSaleItemsContainerDto

    companion object {
        private const val LATEST_ENDPOINT = "cc0071a1-f06e-48fa-9e90-b1c2a61eaca7"
        private const val FLASH_SALE_ENDPOINT = "a9ceeb6e-416d-4352-bde6-2203416576ac"
    }

}