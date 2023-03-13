package com.example.redmuriapp.main_page

import androidx.recyclerview.widget.DiffUtil
import com.example.redmuriapp.retrofit.LatestItemDto

class LatestItemDiffCallback : DiffUtil.ItemCallback<LatestItemDto>() {

    override fun areItemsTheSame(oldItem: LatestItemDto, newItem: LatestItemDto): Boolean =
        oldItem.image_url == newItem.image_url

    override fun areContentsTheSame(oldItem: LatestItemDto, newItem: LatestItemDto): Boolean =
        oldItem==newItem
}