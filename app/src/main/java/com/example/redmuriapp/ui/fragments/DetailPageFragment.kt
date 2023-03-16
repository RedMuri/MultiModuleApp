package com.example.redmuriapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.example.redmuriapp.databinding.FragmentDetailPageBinding
import com.example.redmuriapp.ui.adapters.RvItemImagesAdapter
import com.example.redmuriapp.ui.adapters.VpItemImagesAdapter


class DetailPageFragment : Fragment() {

    private val testImage =
        "https://mlvtgiqzoszz.i.optimole.com/w:auto/h:auto/q:mauto/https://www.lemark.co.uk/wp-content/uploads/Test-Image-800x800.jpg"

    private val args by navArgs<DetailPageFragmentArgs>()

    private var _binding: FragmentDetailPageBinding? = null
    private val binding: FragmentDetailPageBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailPageBinding == null")

    private val vpAdapterItemImages by lazy {
        VpItemImagesAdapter(listOf(args.latestItem.image_url, testImage))
    }
    private val rvAdapterItemImages by lazy {
        RvItemImagesAdapter(listOf(args.latestItem.image_url, testImage))
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
            binding.tvBottomItemPrice.text = (args.latestItem.price * quantity).toString()
        }
        binding.btQuantityMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvBottomItemPrice.text = (args.latestItem.price * quantity).toString()
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
        val item = args.latestItem
        binding.tvItemName.text = item.name
        binding.tvItemPrice.text = item.price.toString()
        binding.tvBottomItemPrice.text = item.price.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
