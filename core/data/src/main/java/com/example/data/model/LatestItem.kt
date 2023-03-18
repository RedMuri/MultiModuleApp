package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatestItem(
    val category: String,
    val name: String,
    val price: Double,
    val image_url: String,
) : Parcelable
