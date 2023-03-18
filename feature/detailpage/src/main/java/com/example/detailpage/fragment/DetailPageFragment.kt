package com.example.detailpage.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.example.data.model.LatestItem
import com.example.detailpage.adapters.RvItemImagesAdapter
import com.example.detailpage.adapters.VpItemImagesAdapter
import com.example.detailpage.databinding.FragmentDetailPageBinding


class DetailPageFragment : Fragment() {

    companion object {
        private const val EXTRA_ITEM = "item"

        fun createBundle(item: LatestItem) =
            Bundle().apply { putParcelable(EXTRA_ITEM, item) }
    }

    private val item: LatestItem by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(EXTRA_ITEM, LatestItem::class.java)
                ?: throw IllegalStateException("No item passed")
        } else {
            arguments?.getParcelable(EXTRA_ITEM) ?: throw IllegalStateException("No item passed")
        }
    }

    private var _binding: FragmentDetailPageBinding? = null
    private val binding: FragmentDetailPageBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailPageBinding == null")

    private val testImage =
        "https://mlvtgiqzoszz.i.optimole.com/w:auto/h:auto/q:mauto/https://www.lemark.co.uk/wp-content/uploads/Test-Image-800x800.jpg"
    private val vpAdapterItemImages by lazy {
        VpItemImagesAdapter(listOf(item.image_url, testImage))
    }
    private val rvAdapterItemImages by lazy {
        RvItemImagesAdapter(listOf(item.image_url, testImage))
    }

    private var quantity = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadItemData()
        setupImages()
        bindOnClickListeners()
    }

    private fun bindOnClickListeners() {
        binding.btQuantityPlus.setOnClickListener {
            quantity++
            binding.tvBottomItemPrice.text =  String.format("$ %s", (item.price * quantity).toString())
        }
        binding.btQuantityMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvBottomItemPrice.text = String.format("$ %s", (item.price * quantity).toString())
            }
        }
        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupImages() {
        val viewPager = binding.vpPhotos
        val photoList = binding.rvPhotos
        viewPager.adapter = vpAdapterItemImages
        photoList.adapter = rvAdapterItemImages
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(photoList)
        rvAdapterItemImages.setSelectedPosition(0)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                rvAdapterItemImages.setSelectedPosition(position)
                photoList.smoothScrollToPosition(position)
            }
        })
        rvAdapterItemImages.onItemClickListener = {
            viewPager.currentItem = it
        }
    }

    private fun uploadItemData() {
        binding.tvItemName.text = item.name
        binding.tvItemPrice.text = String.format("$ %s", item.price.toString())
        binding.tvBottomItemPrice.text = String.format("$ %s", item.price.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
