package com.example.mainpage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.model.LatestItem
import com.example.mainpage.R
import com.example.mainpage.databinding.ItemLatestBinding
import com.squareup.picasso.Picasso

class LatestItemsAdapter() :
    ListAdapter<LatestItem, LatestItemsAdapter.LatestItemViewHolder>(
        LatestItemDiffCallback()
    ) {

    var onItemClickListener: ((LatestItem) -> Unit)? = null

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
                tvItemPrice.text = String.format("$ %s",price.toString())
                Picasso
                    .get()
                    .load(image_url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .centerCrop()
                    .into(ivItemImage)
                root.setOnClickListener {
                    onItemClickListener?.invoke(this)
                }
            }
        }
    }
}
