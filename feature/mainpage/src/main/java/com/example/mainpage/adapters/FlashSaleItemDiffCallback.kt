package com.example.mainpage.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.data.model.FlashSaleItem

class FlashSaleItemDiffCallback : DiffUtil.ItemCallback<FlashSaleItem>() {

    override fun areItemsTheSame(oldItem: FlashSaleItem, newItem: FlashSaleItem): Boolean =
        oldItem.image_url == newItem.image_url

    override fun areContentsTheSame(oldItem: FlashSaleItem, newItem: FlashSaleItem): Boolean =
        oldItem==newItem
}