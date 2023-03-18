package com.example.detailpage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.detailpage.R
import com.example.detailpage.databinding.VpItemImageBinding
import com.squareup.picasso.Picasso

class VpItemImagesAdapter(var images: List<String>) :
    Adapter<VpItemImagesAdapter.ItemImageViewHolder>() {

    class ItemImageViewHolder(val binding: VpItemImageBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageViewHolder {
        val binding = VpItemImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemImageViewHolder(binding)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ItemImageViewHolder, position: Int) {
        with(holder.binding) {
            with(images[position]) {
                Picasso
                    .get()
                    .load(this)
                    .into(ivItemImage)
            }
        }
    }
}
