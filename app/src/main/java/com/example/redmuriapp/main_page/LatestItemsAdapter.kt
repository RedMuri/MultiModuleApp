package com.example.redmuriapp.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.redmuriapp.databinding.ItemLatestBinding
import com.example.redmuriapp.retrofit.LatestItemDto

class LatestItemsAdapter() :
    ListAdapter<LatestItemDto, LatestItemsAdapter.LatestItemViewHolder>(LatestItemDiffCallback()) {

    var onItemClickListener: ((LatestItemDto) -> Unit)? = null

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
                root.setOnClickListener {
                    onItemClickListener?.invoke(this)
                }
            }
        }
    }
}
