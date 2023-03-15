package com.example.redmuriapp.domain.entities

data class FlashSaleItem(
    var category: String,
    var name: String,
    var price: Double,
    var discount: Double,
    var image_url: String,
)
