package com.example.redmuriapp.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatestItemDto(
    val category: String,
    val name: String,
    val price: Double,
    val image_url: String,
) : Parcelable
