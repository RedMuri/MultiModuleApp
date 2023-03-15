package com.example.redmuriapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.redmuriapp.data.network.models.FlashSaleItemDto

class FlashSaleItemDiffCallback : DiffUtil.ItemCallback<FlashSaleItemDto>() {

    override fun areItemsTheSame(oldItem: FlashSaleItemDto, newItem: FlashSaleItemDto): Boolean =
        oldItem.image_url == newItem.image_url

    override fun areContentsTheSame(oldItem: FlashSaleItemDto, newItem: FlashSaleItemDto): Boolean =
        oldItem==newItem
}