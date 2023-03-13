package com.example.redmuriapp.main_page

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.ItemLatestBinding
import com.example.redmuriapp.retrofit.LatestItemDto
import com.squareup.picasso.Picasso

class LatestItemsAdapter(
    val application: Application,
) :
    ListAdapter<LatestItemDto, LatestItemsAdapter.LatestItemViewHolder>(LatestItemDiffCallback()) {

    class LatestItemViewHolder(val binding: ItemLatestBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestItemViewHolder {
        val binding = ItemLatestBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestItemViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvItemCategory.text = category
                tvItemName.text = name
                tvItemPrice.text = price.toString()
//                Picasso
//                    .get()
//                    .load(image_url)
//                    .placeholder(R.drawable.ic_apple)
//                    .into(ivItemImage)
            }
        }
    }
}
