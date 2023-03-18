package com.example.detailpage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.detailpage.R
import com.example.detailpage.databinding.RvItemImageNotSelectedBinding
import com.example.detailpage.databinding.RvItemImageSelectedBinding
import com.squareup.picasso.Picasso

class RvItemImagesAdapter(var images: List<String>) :
    Adapter<RvItemImagesAdapter.RvItemImageViewHolder>() {

    private var selectedPosition = 0

    var onItemClickListener: ((Int) -> Unit)? = null

    class RvItemImageViewHolder(val binding: ViewBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvItemImageViewHolder {
        val binding = when (viewType) {
            VIEW_TYPE_NOT_SELECTED -> {
                RvItemImageNotSelectedBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            }
            VIEW_TYPE_SELECTED -> {
                RvItemImageSelectedBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            }
            else -> {
                throw RuntimeException("Unknown view type: $viewType")
            }
        }
        return RvItemImageViewHolder(binding)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: RvItemImageViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
        val item = images[position]
        if (holder.binding is RvItemImageNotSelectedBinding) {
            Picasso
                .get()
                .load(item)
                .fit()
                .centerCrop()
                .into(holder.binding.ivItemImage)
        } else if (holder.binding is RvItemImageSelectedBinding) {
            Picasso
                .get()
                .load(item)
                .fit()
                .centerCrop()
                .into(holder.binding.ivItemImage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (selectedPosition == position) VIEW_TYPE_SELECTED else VIEW_TYPE_NOT_SELECTED
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    companion object {

        const val VIEW_TYPE_SELECTED = 1
        const val VIEW_TYPE_NOT_SELECTED = 0
    }
}
