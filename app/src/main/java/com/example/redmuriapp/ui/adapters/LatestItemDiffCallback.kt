package com.example.redmuriapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.redmuriapp.data.network.models.LatestItemDto

class LatestItemDiffCallback : DiffUtil.ItemCallback<LatestItemDto>() {

    override fun areItemsTheSame(oldItem: LatestItemDto, newItem: LatestItemDto): Boolean =
        oldItem.image_url == newItem.image_url

    override fun areContentsTheSame(oldItem: LatestItemDto, newItem: LatestItemDto): Boolean =
        oldItem==newItem
}