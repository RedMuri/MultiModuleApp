package com.example.redmuriapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.redmuriapp.databinding.ItemFlashSaleBinding
import com.example.redmuriapp.domain.entities.FlashSaleItem

class FlashSaleItemsAdapter() :
    ListAdapter<FlashSaleItem, FlashSaleItemsAdapter.FlashSaleItemViewHolder>(
        FlashSaleItemDiffCallback()
    ) {

    var onItemClickListener: ((FlashSaleItem) -> Unit)? = null

    class FlashSaleItemViewHolder(val binding: ItemFlashSaleBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleItemViewHolder {
        val binding = ItemFlashSaleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FlashSaleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlashSaleItemViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvItemCategory.text = category
                tvItemName.text = name
                tvItemPrice.text = price.toString()
                tvItemDiscount.text = discount.toString()
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
