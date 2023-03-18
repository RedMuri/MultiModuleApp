package com.example.mainpage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.model.FlashSaleItem
import com.example.mainpage.R
import com.example.mainpage.databinding.ItemFlashSaleBinding
import com.squareup.picasso.Picasso

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
                tvItemPrice.text = String.format("$ %s",price.toString())
                tvItemDiscount.text = String.format("%d%% off",discount.toInt())
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
